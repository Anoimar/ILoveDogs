package com.thernat.ilovedogs.ui

import androidx.lifecycle.MutableLiveData
import com.thernat.ilovedogs.base.BaseViewModel
import com.thernat.ilovedogs.breed.Breed

/**
 * Created by m.rafalski@matsuu.com on 10/03/2019.
 */
class BreedViewModel:BaseViewModel() {

    private val breedName = MutableLiveData<String>()
    private val bredFor = MutableLiveData<String>()

    fun bind(breed: Breed){
        breedName.value = breed.name
        bredFor.value = breed.bred_for
    }

    fun getBreedName():MutableLiveData<String>{
        return breedName
    }

    fun getBreedFor():MutableLiveData<String>{
        return bredFor
    }
}