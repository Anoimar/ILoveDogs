package com.thernat.ilovedogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.thernat.ilovedogs.databinding.ActivityMainBinding
import com.thernat.ilovedogs.ui.BreedListViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BreedListViewModel

    private var errorSnackbar: Snackbar? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvBreeds.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        viewModel = ViewModelProviders.of(this).get(BreedListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showFailure(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showFailure(@StringRes errorMessage: Int){
        errorSnackbar = Snackbar.make(binding.root,errorMessage,Snackbar.LENGTH_INDEFINITE)
            .apply {
                setAction(R.string.retry,viewModel.errorClickListener)
                show()
            }
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}
