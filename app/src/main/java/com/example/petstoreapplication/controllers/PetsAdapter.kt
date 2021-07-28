package com.example.petstoreapplication.controllers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petstoreapplication.R
import com.example.petstoreapplication.interfaces.ApiService
import com.example.petstoreapplication.models.Constants
import com.example.petstoreapplication.models.Pet.Pet
import kotlinx.android.synthetic.main.item_list_pets.view.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PetsAdapter (val pet:List<Pet>) :RecyclerView.Adapter<PetsAdapter.PetsHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PetsHolder(layoutInflater.inflate(R.layout.item_list_pets,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: PetsHolder, position: Int) {
        holder.bind(pet[position])
    }

    override fun getItemCount(): Int {
        return pet.size
    }

    class PetsHolder(val view : View,val context : Context):RecyclerView.ViewHolder(view){
        fun bind(pet :Pet){
            view.textViewItemId.text = pet.id.toString()
            view.textViewItemName.text = pet.name
            view.textViewItemStatus.text = pet.status
        }
    }
}