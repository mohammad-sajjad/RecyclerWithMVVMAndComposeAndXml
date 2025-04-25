package com.blueapp_compose

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.blueapp_compose.adapter.ImageListAdapter
import com.blueapp_compose.adapter.SliderAdapter
import com.blueapp_compose.core.DataFetchState
import com.blueapp_compose.models.ImagesItems
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<ImageListViewModel>()
    private val adapter by lazy { ImageListAdapter() }
    private val sliderAdapter by lazy { SliderAdapter() }
    private lateinit var recyclerView: RecyclerView
    private lateinit var slider: ViewPager2
    private lateinit var searchView: EditText
    private lateinit var dotIndicator: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recycler_view)
        slider = findViewById(R.id.viewPager)
        searchView = findViewById(R.id.search_view)
        dotIndicator = findViewById(R.id.dot_indicator)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setPagination(true) {
            viewModel.getImages(viewModel.currentQuery)
        }

        slider.adapter = sliderAdapter

        observeImagesData()
        observeSliderItems()
        listener()

        viewModel.stateMachine.observe(this) {
            when(it) {
                is DataFetchState.Error -> {
                    Log.e("TAG", "onCreate: ${it.error}")
                }
                DataFetchState.Loading -> {
                    Log.e("TAG", "onCreate: Loading")
                }
                DataFetchState.Success -> {
                    Log.e("TAG", "onCreate: Success")
                }

                DataFetchState.Idle -> {
                    Log.e("TAG", "onCreate: Idle", )
                }
            }
        }

        viewModel.getImages("cars", initialLoad = true)
    }

    private fun listener() {
        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onSliderItemChanged(position)
                updateDotIndicator(position)
            }
        })

        searchView.addTextChangedListener() { text ->
            text?.let {
                viewModel.filterImages(it.toString())
            }
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            showStatisticsBottomSheet(adapter.getItems() as List<ImagesItems>, viewModel.currentPage-1)
        }
    }
    private fun observeSliderItems() {
        viewModel.sliderItems.observe(this, Observer { images ->
            createDots(images.size)
            sliderAdapter.updateData(images.toMutableList())
        })
    }

    private fun observeImagesData() {
        viewModel.images.observe(this, Observer { imageList ->
            adapter.updateData(imageList.toMutableList())
            adapter.onLoadMoreFinished()
            Log.e("TAG", "observeLiveData: $imageList")
//            if (imageList.isEmpty()) {
//                noDataText.visibility = TextView.VISIBLE
//            } else {
//                noDataText.visibility = TextView.GONE
//            }
        })
    }

    private fun createDots(count: Int) {
        dotIndicator.removeAllViews()
        for (i in 0 until count) {
            val dot = ImageView(this).apply {
                setImageResource(R.drawable.dot_inactive)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(8, 0, 8, 0)
                }
            }
            dotIndicator.addView(dot)
        }
        updateDotIndicator(0)
    }

    private fun updateDotIndicator(selectedIndex: Int) {
        for (i in 0 until dotIndicator.childCount) {
            val dot = dotIndicator.getChildAt(i) as ImageView
            dot.setImageResource(
                if (i == selectedIndex) R.drawable.dot_active
                else R.drawable.dot_inactive
            )
        }
    }

    private fun showStatisticsBottomSheet(data: List<ImagesItems>, page: Int = 1) {
        val dialog = BottomSheetDialog(this)
        val binding = layoutInflater.inflate(R.layout.bottomsheet_statistics, null)

        // Page info
        val itemCount = data.size
        binding.findViewById<TextView>(R.id.page_info_tv).text = "Page $page ($itemCount items)"

        // Character frequency
        val charFrequency = data.joinToString("")
            .lowercase()
            .filter { it.isLetter() }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .take(3)
            .joinToString("\n") { "${it.first} = ${it.second}" }

        binding.findViewById<TextView>(R.id.stats_tv).text = charFrequency

        dialog.setContentView(binding)
        dialog.show()
    }
}
