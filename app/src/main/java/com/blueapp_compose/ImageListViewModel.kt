package com.blueapp_compose

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blueapp_compose.core.DataFetchState
import com.blueapp_compose.core.StateMachine
import com.blueapp_compose.error.StandardError
import com.blueapp_compose.models.ImagesItems
import com.blueapp_compose.models.SliderItems
import com.blueapp_compose.remote.ApiResponseCallback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ImageListViewModel class.
 */
@HiltViewModel
class ImageListViewModel @Inject constructor(private val imageRepository: ImageRepository, @ApplicationContext private val context: Context) : ViewModel() {

    private val _images = MutableLiveData<MutableList<ImagesItems>>(mutableListOf())
    val images: LiveData<MutableList<ImagesItems>> = _images

    private val _sliderItems = MutableLiveData<List<SliderItems>>()
    val sliderItems: LiveData<List<SliderItems>> get() = _sliderItems


    val stateMachine = StateMachine()

    var currentPage = 1
    var currentQuery = ""
    private var isFetching = false
    private var allLoadedImages: MutableList<ImagesItems> = mutableListOf() // Holds all loaded items


    init {
        loadImagesFromJson()
    }

    // 5.  Function to fetch images using the repository
    fun getImages(query: String, initialLoad: Boolean = false) {
        if (isFetching) return  // Prevent multiple requests
        isFetching = true
        if (initialLoad) {
            currentPage = 1;
            _images.value = mutableListOf()
            allLoadedImages.clear()
            stateMachine.value = DataFetchState.Loading
        }

        else if (currentPage == 1) {
            stateMachine.value = DataFetchState.Loading
        }
        else {
            stateMachine.value = DataFetchState.Loading
        }
        currentQuery = query

            imageRepository.getImages(currentPage, 20, query, object : ApiResponseCallback<ImagesResponseModel> {
                override fun onSuccess(response: ImagesResponseModel) {
                    isFetching = false
                    if (response.hits.isEmpty()) {
                        if (currentPage == 1) {
                            stateMachine.postValue(DataFetchState.Error(StandardError("No Data Found", "No Data Found")))
                        }
                    } else {
                        val imageListItems = transformDataToImageListItem(response)
                        allLoadedImages.addAll(imageListItems)
                        val currentList = _images.value ?: mutableListOf()
                        currentList.addAll(imageListItems)
                        _images.value = currentList
                        stateMachine.value = DataFetchState.Success
                        currentPage++
                    }
                }

                override fun onError(error: StandardError) {
                    isFetching = false
                    stateMachine.value = DataFetchState.Error(error)
                }
            })
    }


    private fun transformDataToImageListItem(data: ImagesResponseModel): MutableList<ImagesItems> {
        return data.hits.map { hit ->
            ImagesItems(hit.largeImageURL, hit.user, hit.tags)
        }.toMutableList()
    }

    fun readJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun loadImagesFromJson() {
        val jsonString = readJsonFromAssets("categories.json")
        val gson = Gson()
        val sliderItems: List<SliderItems> = gson.fromJson(jsonString, object : TypeToken<List<SliderItems>>() {}.type)
        _sliderItems.postValue(sliderItems)
    }


    fun onSliderItemChanged(position: Int) {
        val selectedItem = _sliderItems.value?.getOrNull(position)
        selectedItem?.let {
            resetPagination() // Reset page and clear image list
            getImages(it.categoryName, initialLoad = true) // Trigger API using name or any relevant key
        }
    }

    // Function to filter images based on the search query
    fun filterImages(query: String) {
        val filteredList = allLoadedImages.filter {
            it.title.contains(query, ignoreCase = true)
        }
        _images.value = filteredList.toMutableList()
    }

    fun resetPagination() {
        currentPage = 1
        _images.value = mutableListOf()
    }

}