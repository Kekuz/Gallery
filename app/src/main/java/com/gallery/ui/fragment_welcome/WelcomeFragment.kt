package com.gallery.ui.fragment_welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentWelcomeBinding
import com.gallery.ui.fragment_profile.recycler.ProfilePictureAdapter
import com.gallery.ui.fragment_sing_up.SingUpView
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


@AndroidEntryPoint
class WelcomeFragment : MvpAppCompatFragment(), WelcomeView {
    private lateinit var binding: FragmentWelcomeBinding

    @Inject
    lateinit var presenterProvider: Provider<WelcomePresenter>

    private val presenter: WelcomePresenter by moxyPresenter { presenterProvider.get() }


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

        presenter.checkAuth()
        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_singInFragment)
        }

        binding.btnSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_singUpFragment)
        }
    }

    override fun navigateAuth() {
        findNavController().navigate(R.id.action_welcomeFragment_to_navigation_main)
    }

}