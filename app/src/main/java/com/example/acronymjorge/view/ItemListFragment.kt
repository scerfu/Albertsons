package com.example.acronymjorge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymjorge.R
import com.example.acronymjorge.databinding.FragmentItemListBinding
import com.example.acronymjorge.utils.UIEvents
import com.example.acronymjorge.view.adapter.AcronymAdapter
import com.example.acronymjorge.viewmodel.AcronymViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AcronymViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_item_list,
            container,
            false
        )
        binding.acronymViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@BindingAdapter("app:meanings")
fun meanings(recyclerView: RecyclerView, uiEvents: UIEvents) {
    when (uiEvents) {
        is UIEvents.ERROR -> {
            Toast.makeText(recyclerView.context, "Error occuurred", Toast.LENGTH_SHORT).show()
        }
        is UIEvents.LOADING -> {}
        is UIEvents.SUCCESS -> {
            if (uiEvents.response.isEmpty()) {
                recyclerView.adapter = AcronymAdapter(emptyList())
                Toast.makeText(
                    recyclerView.context,
                    "Empty Data Response for Acronym",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                uiEvents.response.first().lfs?.let {
                    recyclerView.adapter = AcronymAdapter(it)
                }
            }
        }
    }
}