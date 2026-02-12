package com.example.productformretrofit.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productformretrofit.R
import com.example.productformretrofit.ui.viewModel.ViewModelProduct
import com.example.productformretrofit.databinding.FragmentShowProductsRVBinding
import com.example.productformretrofit.modle.StatesUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowProductsRV : Fragment() {
  private lateinit var binding:FragmentShowProductsRVBinding
  private lateinit var adapter : AdapterProduct

  private val viewModel : ViewModelProduct by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        تخبر ال toolBar انfragment  لديها menu تريد عرضه فيه
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        عمل انفلات للمنيو المراد عرضها
        inflater.inflate(R.menu.menu_products,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//         اذا المستخدم ضغط الزر
        return when(item.itemId){
            R.id.actionRefreshId -> {
                viewModel.refreshProduct()
                true
            }else ->
                super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        استدعا دالة التحديث
        viewModel.refreshProduct()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShowProductsRVBinding.inflate(inflater,container ,false)

        adapter = AdapterProduct{
            productId ->
//            عند النقر علي زر عرض تفاصيل
            val action = ShowProductsRVDirections.actionShowProductsRVToShoewDetailsProduct(productId)
            findNavController().navigate(action)
        }
//         طريقة عرض المنتجات في receclerView
        binding.rvShowProductId.adapter = adapter
        binding.rvShowProductId .layoutManager = LinearLayoutManager(requireContext())


        viewModel.productsState.observe(viewLifecycleOwner){
            state ->
            when(state){
                is StatesUI.LoadingStates ->
                {
                    binding.progressBarIdShowRV.isVisible = true
                }
                is StatesUI.Success ->
                {
                    binding.progressBarIdShowRV.isVisible = false
                    adapter.submitList(state.data)
                }
                is StatesUI.ErrorStates ->
                {
                    binding.progressBarIdShowRV.isVisible = false
                    Toast.makeText(requireContext(),state.messageError, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.loadProducts()

        return binding.root
    }

}