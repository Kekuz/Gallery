package com.gallery.ui.fragment_welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    //TODO Сделать проверку авторизации в shared prefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_singInFragment)
        }

        binding.btnSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_singUpFragment)
        }
    }

}