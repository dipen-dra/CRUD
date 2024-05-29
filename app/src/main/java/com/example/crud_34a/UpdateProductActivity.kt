package com.example.crud_34a

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.databinding.ActivityUpdateProductBinding
import com.example.crud_34a.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateProductActivity : AppCompatActivity() {
    lateinit var updateProductBinding: ActivityUpdateProductBinding
    var id = ""
    var firebaseDatabase : FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref : DatabaseReference = firebaseDatabase.reference.child("products")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        updateProductBinding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(updateProductBinding.root)

        var product : ProductModel? = intent.getParcelableExtra("product")

        updateProductBinding.editTextNameUpdate.setText(product?.productName)
        updateProductBinding.editTextPriceUpdate.setText(product?.productPrice.toString())
        updateProductBinding.editTextDescUpdate.setText(product?.productDesc)

        id = product?.id.toString()

        updateProductBinding.buttonUpdate.setOnClickListener {
            var updatedName : String= updateProductBinding.editTextNameUpdate.text.toString()
            var updatedPrice: Int = updateProductBinding.editTextPriceUpdate.text.toString().toInt()
            var updatedDesc : String = updateProductBinding.editTextDescUpdate.text.toString()

            var updatedMap = mutableMapOf<String,Any>()
            updatedMap["productName"] = updatedName
            updatedMap["productPrice"] = updatedPrice
            updatedMap["productDesc"] = updatedDesc
            updatedMap["id"] = id

            ref.child(id).updateChildren(updatedMap).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Data updated"
                        ,Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext,it.exception?.message
                        ,Toast.LENGTH_LONG).show()
                }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}