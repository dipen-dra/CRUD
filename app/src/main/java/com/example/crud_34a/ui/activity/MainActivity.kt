package com.example.crud_34a.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_34a.R
import com.example.crud_34a.adapter.ProductAdapter
import com.example.crud_34a.databinding.ActivityMainBinding
import com.example.crud_34a.repository.ProductRepositoryImpl
import com.example.crud_34a.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    lateinit var  productViewModel: ProductViewModel
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        productAdapter= ProductAdapter(this@MainActivity,ArrayList())

        var repo=ProductRepositoryImpl()
        productViewModel = ProductViewModel(repo)

        productViewModel.fetchAllProducts()

        productViewModel.productList.observe(this){
            it?.let{products->
                productAdapter.updateData(products)
                mainBinding.recyclerView.layoutManager =
                    LinearLayoutManager(this@MainActivity)
                mainBinding.recyclerView.adapter = productAdapter
            }
        }

        productViewModel.loadingState.observe(this){loadingState->
            if(loadingState){
                mainBinding.progressBar2.visibility = View.VISIBLE
            }else{
                mainBinding.progressBar2.visibility = View.GONE
            }
        }

        mainBinding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productAdapter
        }




        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var id = productAdapter.getProductID(viewHolder.adapterPosition)
                var imageName = productAdapter.getImageName(viewHolder.adapterPosition)
                productViewModel.deleteProducts(id) { success, message ->
                    if (success) {
                        productViewModel.deleteImage(imageName) { success, message ->
                            if (success) {
                                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    } else {
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                }

        }).attachToRecyclerView(mainBinding.recyclerView)


        mainBinding.floatingActionButton.setOnClickListener {
            var intent = Intent(this@MainActivity,
                AddProductActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnsensorLst)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}