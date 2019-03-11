package com.thernat.ilovedogs.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.thernat.ilovedogs.BuildConfig
import com.thernat.ilovedogs.base.BaseViewModel
import com.thernat.ilovedogs.network.MainApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import com.thernat.ilovedogs.R
import com.thernat.ilovedogs.breed.Breed
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by m.rafalski@matsuu.com on 10/03/2019.
 */
class BreedListViewModel: BaseViewModel() {

    @Inject
    lateinit var mainApiService: MainApiService

    private lateinit var dogApiSubscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    val errorClickListener = View.OnClickListener { loadDogs() }

    val breedListAdapter: BreedListAdapter = BreedListAdapter()



    init {
        loadDogs()
    }

    private fun loadDogs() {
        dogApiSubscription = mainApiService.getBreeds(BuildConfig.DOGS_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStartedDownloadingBreedList() }
            .doOnTerminate{ onStoppedDownloadingBreedList() }
            .subscribe({
                onDownloadedBreedListSuccess(it)
            },{t->
                Log.e("BLVM","Failed to display breed list",t)
                onDownloadedBreedListError()
            })
    }



    private fun onStartedDownloadingBreedList() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onStoppedDownloadingBreedList() {
        loadingVisibility.value = View.GONE
    }


    private fun onDownloadedBreedListSuccess(breeds: List<Breed>) {
        breedListAdapter.updateBreedsList(breeds)
    }


    private fun onDownloadedBreedListError() {
        errorMessage.value = R.string.loading_failed
    }

    override fun onCleared() {
        super.onCleared()
        dogApiSubscription.dispose()
    }
}