package com.gallery.ui.fragment_welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentWelcomeBinding
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
            presenter.navigateToSingIn()
        }

        binding.btnSingUp.setOnClickListener {
            presenter.navigateToSingUp()
        }
    }

    override fun navigateAuth() {
        findNavController().navigate(R.id.action_welcomeFragment_to_navigation_main)
    }

    override fun navigateToSingIn() {
        findNavController().navigate(R.id.action_welcomeFragment_to_singInFragment)
    }

    override fun navigateToSingUp() {
        findNavController().navigate(R.id.action_welcomeFragment_to_singUpFragment)
    }

}