package com.gallery.ui.fragment_sing_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentSingUpBinding
import com.gallery.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingUpFragment : Fragment() {

    private lateinit var binding: FragmentSingUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_mainFragment)
        }

        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}