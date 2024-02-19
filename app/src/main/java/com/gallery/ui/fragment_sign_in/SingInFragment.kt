package com.gallery.ui.fragment_sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentSingInBinding

class SingInFragment : Fragment() {

    private lateinit var binding: FragmentSingInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_singInFragment_to_mainFragment)
        }

        binding.btnSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_singInFragment_to_singUpFragment)
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}