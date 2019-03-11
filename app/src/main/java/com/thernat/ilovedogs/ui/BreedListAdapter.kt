package com.thernat.ilovedogs.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thernat.ilovedogs.breed.Breed
import com.thernat.ilovedogs.R
import com.thernat.ilovedogs.databinding.ItemBreedBinding

/**
 * Created by m.rafalski@matsuu.com on 10/03/2019.
 */
class BreedListAdapter : RecyclerView.Adapter<BreedListAdapter.BreedViewHolder>() {

    private lateinit var breeds:List<Breed>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding: ItemBreedBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context),
                R.layout.item_breed,parent,false)
        return BreedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::breeds.isInitialized) breeds.size else 0
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(breeds[position])
    }


    fun updateBreedsList(breeds: List<Breed>){
        this.breeds = breeds
        notifyDataSetChanged()
    }

    class BreedViewHolder(private val binding: com.thernat.ilovedogs.databinding.ItemBreedBinding): RecyclerView.ViewHolder(binding.root){

        private val viewModel = BreedViewModel()

        fun bind(breed: Breed){
            viewModel.bind(breed)
            binding.viewModel = viewModel
        }
    }
}