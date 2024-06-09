package com.example.crud_34a.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.crud_34a.model.ProductModel
import com.example.crud_34a.repository.ProductRepository

class ProductViewModel(val repository: ProductRepository) : ViewModel() {

    fun uploadImages(imageUri: Uri, callback: (Boolean, String?, String?) -> Unit) {
        repository.uploadImages(imageUri) { success, imageUrl, imageName ->
            callback(success, imageUrl, imageName)
        }
    }
    fun addProducts(productModel: ProductModel, callback: (Boolean, String?) -> Unit){
        repository.addProducts(productModel,callback)
    }
}