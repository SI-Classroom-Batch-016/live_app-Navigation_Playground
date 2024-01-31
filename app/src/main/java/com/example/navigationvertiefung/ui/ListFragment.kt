package com.example.navigationvertiefung.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationvertiefung.MainViewmodel
import com.example.navigationvertiefung.data.DataSource
import com.example.navigationvertiefung.databinding.FragmentListBinding
import com.example.navigationvertiefung.ui.adapter.DataAdapter

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    val viewmodel: MainViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DataAdapter(findNavController(), DataSource.getData())
        binding.dataRV.adapter = adapter
    }
}