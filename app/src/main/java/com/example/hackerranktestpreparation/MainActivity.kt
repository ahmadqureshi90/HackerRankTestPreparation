package com.example.hackerranktestpreparation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hackerranktestpreparation.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLiveDataObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.btnAction.setOnClickListener {
            viewModel.getResponseFromApi()
        }
    }

    private fun initLiveDataObservers() {
        viewModel.loadingViewLiveData.observe(this) { showLoading ->
            if (showLoading) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }

        viewModel.apiRequestLiveData.observe(this)
        { response ->
            response.items?.let { dataList ->
                binding.rvMain.adapter = DataListAdapter(this@MainActivity, dataList)
            }
        }
    }
}