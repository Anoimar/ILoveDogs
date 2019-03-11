package com.thernat.ilovedogs.injection.component

import com.thernat.ilovedogs.injection.module.NetworkModule
import com.thernat.ilovedogs.ui.BreedListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by m.rafalski@matsuu.com on 10/03/2019.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(breedListViewModel: BreedListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}