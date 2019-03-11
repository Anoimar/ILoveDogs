package com.thernat.ilovedogs.base

import androidx.lifecycle.ViewModel
import com.thernat.ilovedogs.injection.component.DaggerViewModelInjector
import com.thernat.ilovedogs.injection.component.ViewModelInjector
import com.thernat.ilovedogs.injection.module.NetworkModule
import com.thernat.ilovedogs.ui.BreedListViewModel

/**
 * Created by m.rafalski@matsuu.com on 10/03/2019.
 */
abstract class BaseViewModel: ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is BreedListViewModel -> injector.inject(this)
        }
    }

}