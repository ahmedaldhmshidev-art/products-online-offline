package com.example.productformretrofit.adapterHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productformretrofit.R
import com.example.productformretrofit.databinding.ItemShowProductBinding
import com.example.productformretrofit.modle.Product

class AdapterProduct(private val onClick : (Int) -> Unit): RecyclerView.Adapter<AdapterProduct.HolderProduct>() {

    private val _products = mutableListOf<Product>()

    fun submitList(list: List<Product>){
        _products.clear()
        _products.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProduct.HolderProduct
    {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_show_product ,parent ,false)
        return HolderProduct(view)
    }

    override fun onBindViewHolder(holder: AdapterProduct.HolderProduct, position: Int)
    {
        val data= _products[position]
        holder.build(data)
    }

    override fun getItemCount(): Int = _products.size


    inner class HolderProduct(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemShowProductBinding.bind(itemView)

        fun build(product : Product){
            binding.tvTitleIdCard .text = product.title
            binding.tvPriceIdCard .text = "$${product.price}"

            Glide.with(itemView.context).load(product.image).into(binding.imgVProductIdCard)

            binding.tvDescriptionIdCard.setOnClickListener {
                onClick(product.id)
            }
        }
    }

}