package com.example.petstoreapplication.ui.listPets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petstoreapplication.controllers.LoadingDialog
import com.example.petstoreapplication.controllers.PetsAdapter
import com.example.petstoreapplication.databinding.FragmentHomeBinding
import com.example.petstoreapplication.interfaces.ApiService
import com.example.petstoreapplication.models.Constants
import com.example.petstoreapplication.models.Pet.Pet
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListPetsFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitListPets()

    }

    private fun retrofitListPets() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val loading = LoadingDialog(this)
        loading.startDialog()
        service.getListPets("available").enqueue(object :Callback<List<Pet>>{
            override fun onResponse(call: Call<List<Pet>>, response: Response<List<Pet>>) {
                val list : MutableList<Pet> = ArrayList()
                val PetList = response.body()

                for (petList in PetList!!){
                    var pet = Pet()

                    pet.id = petList.id
                    pet.category = petList.category
                    pet.name = petList.name
                    pet.photoUrls = petList.photoUrls
                    pet.tags = petList.tags
                    pet.status = petList.status
                    list.add(pet)
                }
                recyclerViewPets.layoutManager = LinearLayoutManager(requireContext())
                val adapter = PetsAdapter(list)
                recyclerViewPets.adapter = adapter
                loading.isDismiss()

            }

            override fun onFailure(call: Call<List<Pet>>, t: Throwable) {
                Log.e("Error>>",t.message.toString())
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}