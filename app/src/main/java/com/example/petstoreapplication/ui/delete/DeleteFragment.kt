package com.example.petstoreapplication.ui.delete

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petstoreapplication.databinding.FragmentDeleteBinding
import com.example.petstoreapplication.interfaces.ApiService
import com.example.petstoreapplication.models.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.buttonCreate
import kotlinx.android.synthetic.main.fragment_delete.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DeleteFragment : Fragment() {
    private var _binding: FragmentDeleteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitDeletePet()
    }

    private fun retrofitDeletePet() {
        buttonDelete.setOnClickListener {
            if (editTextDeleteId.text.toString().isEmpty()) {
                Snackbar.make(it, "debe ingresar los datos", Snackbar.LENGTH_LONG).show()
            } else {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                 val service = retrofit.create(ApiService::class.java)
                service.deletePet(editTextDeleteId.text.toString().toInt()).enqueue(object :Callback<Void>{
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.code() == 200){
                            Snackbar.make(it,"Dato Eliminado",Snackbar.LENGTH_LONG).show()
                            editTextDeleteId.setText("")
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Snackbar.make(it,"Error al Eliminar ",Snackbar.LENGTH_LONG).show()
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
