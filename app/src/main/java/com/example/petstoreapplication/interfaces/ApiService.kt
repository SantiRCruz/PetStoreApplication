package com.example.petstoreapplication.interfaces

import com.example.petstoreapplication.models.Pet.Pet
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("pet/findByStatus")
    fun getListPets(@Query("status")status:String):Call<List<Pet>>

    @POST("pet")
    fun createPet(@Body pet: Pet):Call<Pet>

    @PUT("pet")
    fun updatePet(@Body pet: Pet):Call<Pet>

    @DELETE("pet/{petId}")
    fun deletePet(@Path("petId")petId:Int):Call<Void>
}