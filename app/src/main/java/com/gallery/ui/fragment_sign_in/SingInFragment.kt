package com.gallery.ui.fragment_sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentSingInBinding
import com.gallery.ui.fragment_sing_up.SingUpState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class SingInFragment : MvpAppCompatFragment(), SingInView {

    private lateinit var binding: FragmentSingInBinding

    @Inject
    lateinit var presenterProvider: Provider<SingInPresenter>

    private val presenter: SingInPresenter by moxyPresenter { presenterProvider.get() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindSingInButton()
        bindSingUpButton()
        bindCancelButton()

        clearErrorOnInputPassword()
    }

    override fun renderFieldError(state: SingInState) = with(binding) {
        when (state) {

            is SingInState.EmailField -> {
                tiEmail.error = state.error
            }

            is SingInState.PasswordField -> {
                tiPassword.error = state.error
            }

        }
    }

    override fun navigateBack() {
        findNavController().navigateUp()
    }

    override fun navigateToSingUp() {
        findNavController().navigate(R.id.action_singInFragment_to_singUpFragment)
    }

    override fun navigateToMain() {
        findNavController().navigate(R.id.action_singInFragment_to_mainFragment)
    }

    private fun bindSingInButton() = with(binding) {
        btnSingIn.setOnClickListener {
            lifecycleScope.launch {
                presenter.loginIn(
                    email.text.toString(),
                    password.text.toString(),
                )
            }
        }
    }

    private fun bindSingUpButton() = with(binding) {
        btnSingUp.setOnClickListener {
            presenter.navigateToSingUp()
        }
    }

    private fun bindCancelButton() = with(binding) {
        btnCancel.setOnClickListener {
            presenter.navigateBack()
        }
    }

    private fun clearErrorOnInputPassword() {
        binding.password.doOnTextChanged { _, _, _, _ ->
            binding.tiPassword.error = null
        }
    }
}