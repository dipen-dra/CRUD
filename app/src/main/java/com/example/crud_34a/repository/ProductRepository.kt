package com.example.crud_34a.repository

import android.net.Uri
import com.example.crud_34a.model.ProductModel

interface ProductRepository {
    fun addProducts(productModel: ProductModel,callback:(Boolean,String?)-> Unit)
    fun uploadImages(imageUri : Uri,callback:(Boolean,String?,String?)-> Unit)
}
