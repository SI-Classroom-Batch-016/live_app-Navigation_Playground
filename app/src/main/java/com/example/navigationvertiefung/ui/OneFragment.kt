package com.example.navigationvertiefung.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationvertiefung.MainViewmodel
import com.example.navigationvertiefung.R
import com.example.navigationvertiefung.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    private lateinit var binding : FragmentOneBinding
    val viewmodel: MainViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.oneTV.setOnClickListener {
            findNavController().navigate(R.id.twoFragment)
        }
    }
}