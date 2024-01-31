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
import com.example.navigationvertiefung.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {

    private lateinit var binding : FragmentTwoBinding
    val viewmodel: MainViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.twoTV.setOnClickListener {
            findNavController().navigate(R.id.oneFragment)
        }
    }
}