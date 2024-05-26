package com.example.crud_34a.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_34a.R
import com.example.crud_34a.model.ProductModel

class ProductAdapter (var data :
                      ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var productName : TextView = view.findViewById(R.id.lblName)
        var productPrice : TextView = view.findViewById(R.id.lblPrice)
        var productDesc : TextView = view.findViewById(R.id.lblDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).
                        inflate(R.layout.sample_product,
                         parent,false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productName.text = data[position].productName
        holder.productPrice.text = data[position].productPrice.toString()
        holder.productDesc.text = data[position].productDesc
    }
}