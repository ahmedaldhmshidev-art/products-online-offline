package com.example.productformretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.productformretrofit.VMrepository.FactoryVM
import com.example.productformretrofit.VMrepository.RepositoryProduct
import com.example.productformretrofit.VMrepository.ViewModelProduct
import com.example.productformretrofit.databinding.FragmentShoewDetailsProductBinding
import com.example.productformretrofit.modle.StatesUI

class ShowDetailsProduct : Fragment() {
    private lateinit var binding: FragmentShoewDetailsProductBinding
    private lateinit var viewModel : ViewModelProduct

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShoewDetailsProductBinding.inflate(inflater , container , false)

        val args : ShowDetailsProductArgs by navArgs()
        val productId = args.productId


        val app = requireActivity().application as AppProduct
        val factory = FactoryVM(app.repository)
        viewModel = ViewModelProvider(this, factory)[ViewModelProduct::class.java]

        viewModel.loadProductDetails(productId)

        viewModel.productDetailsState.observe(viewLifecycleOwner){
            stat ->
            when (stat){
                is StatesUI.LoadingStates ->
                {
                    binding.progressIdShowDetails.isVisible = true
                }
                is StatesUI.Success ->
                {
                    binding.progressIdShowDetails.isVisible = false
                    val product = stat . data
                    binding.tvTitleIdDetails.text = product.title
                    binding.tvPriceIdDetails.text = "$${product.price}"
                    binding.tvDescriptionIdDetails.text = product.description
                    Glide.with(requireContext()).load(product.image).into(binding.imgProductIdDetails)
                }
                is StatesUI.ErrorStates ->
                    Toast.makeText(requireContext(),stat.messageError,Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }
}