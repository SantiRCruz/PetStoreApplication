package com.example.petstoreapplication.controllers

import android.app.AlertDialog
import com.example.petstoreapplication.R
import com.example.petstoreapplication.ui.listPets.ListPetsFragment

class LoadingDialog(val homeFragment: ListPetsFragment) {
    lateinit var isDialog:AlertDialog
    fun startDialog(){
        val layoutInflater = homeFragment.layoutInflater
        val dialogView = layoutInflater.inflate(R.layout.loading_item,null)

        val builder = AlertDialog.Builder(homeFragment.requireContext())
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }
    fun isDismiss(){
        isDialog.dismiss()
    }
}