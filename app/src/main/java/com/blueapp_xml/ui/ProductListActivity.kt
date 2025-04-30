package com.blueapp_xml.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.blueapp_xml.R
import com.blueapp_xml.adapter.CategoriesAdapter
import com.blueapp_xml.adapter.ProductsAdapter
import com.blueapp_xml.databinding.ActivityProductListBinding
import com.blueapp_xml.databinding.BottomsheetStatisticsBinding
import com.blueapp_xml.models.ProductItems
import com.blueapp_xml.viewmodels.ProductsViewmodel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductListBinding
    private val viewModel by viewModels<ProductsViewmodel>()
    private val categoryAdapter by lazy { CategoriesAdapter() }
    private val productAdapter by lazy { ProductsAdapter() }
    private var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
        observeCategoryItems()
        observeProductItems()
        listener()
    }

    private fun init() {
        binding.viewpagerCategories.adapter = categoryAdapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.productsRecyclerView.adapter = productAdapter
    }

    private fun listener() {
        binding.viewpagerCategories.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position+1
                viewModel.onSliderItemChanged(position)
                updateDotIndicator(position)
            }
        })

        binding.etSearchView.addTextChangedListener() { text ->
            text?.let {
                viewModel.filterProducts(it.toString())
            }
        }

        binding.btnAllProducts.setOnClickListener {
            viewModel.fetchProducts()
        }

        binding.statisticsFab.setOnClickListener {
            val currentData = viewModel.products.value ?: emptyList()
            showStatisticsBottomSheet(currentData)
        }
    }

    private fun observeCategoryItems() {
        viewModel.categories.observe(this) {
            createDots(it.size)
            categoryAdapter.updateData(it.toMutableList())
        }
    }

    private fun observeProductItems() {
        viewModel.products.observe(this) {
            productAdapter.updateData(it.toMutableList())
        }
    }

    private fun createDots(count: Int) {
        binding.llDotIndicator.removeAllViews()
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
            binding.llDotIndicator.addView(dot)
        }
        updateDotIndicator(0)
    }

    private fun updateDotIndicator(selectedIndex: Int) {
        for (i in 0 until binding.llDotIndicator.childCount) {
            val dot = binding.llDotIndicator.getChildAt(i) as ImageView
            dot.setImageResource(
                if (i == selectedIndex) R.drawable.dot_active
                else R.drawable.dot_inactive
            )
        }
    }

    private fun showStatisticsBottomSheet(data: List<ProductItems>) {
        val dialog = BottomSheetDialog(this)
        val binding = BottomsheetStatisticsBinding.inflate(layoutInflater)

        val itemCount = data.size
        binding.pageInfoTv.text = getString(R.string.page_info, currentPage, itemCount)


        val charFrequency = data.joinToString("")
            .lowercase()
            .filter { it.isLetter() }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .take(3)
            .joinToString("\n") { "${it.first} = ${it.second}" }

        binding.statsTv.text = charFrequency

        dialog.setContentView(binding.root)
        dialog.show()
    }

}