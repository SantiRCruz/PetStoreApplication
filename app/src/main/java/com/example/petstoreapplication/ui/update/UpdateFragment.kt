package com.example.petstoreapplication.ui.update

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petstoreapplication.databinding.FragmentNotificationsBinding
import com.example.petstoreapplication.interfaces.ApiService
import com.example.petstoreapplication.models.Constants
import com.example.petstoreapplication.models.Pet.ElseJson
import com.example.petstoreapplication.models.Pet.Pet
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.buttonCreate
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpdateFragment : Fragment() {


    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitUpdatePet()
    }

    private fun retrofitUpdatePet() {
        buttonUpdate.setOnClickListener {
            if (editTextUpdateName.text.toString().isEmpty() || editTextUpdateId.text.toString().isEmpty()  ){
                Snackbar.make(it,"debe ingresar los datos", Snackbar.LENGTH_LONG).show()
            }else{
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                service.updatePet(Pet(editTextUpdateId.text.toString().toLong(),
                    ElseJson(0,""),editTextUpdateName.text.toString
                (), arrayListOf(""), ArrayList<ElseJson>(0),"")).enqueue(object :Callback<Pet>{
                    override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                        if (response.code() == 200){
                            Snackbar.make(it,"Dato Actualizado",Snackbar.LENGTH_LONG).show()
                            editTextUpdateId.setText("")
                            editTextUpdateName.setText("")
                        }
                    }

                    override fun onFailure(call: Call<Pet>, t: Throwable) {
                        Snackbar.make(it,"no se puedo Actualizar el dato ",Snackbar.LENGTH_LONG).show()
                        Log.e("Error>>",t.message.toString())
                    }

                })
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}