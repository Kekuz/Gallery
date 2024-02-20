package com.gallery.ui.fragment_sing_up

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentSingUpBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class SingUpFragment : MvpAppCompatFragment(), SingUpView {

    private lateinit var binding: FragmentSingUpBinding

    @Inject
    lateinit var presenterProvider: Provider<SingUpPresenter>

    private val presenter: SingUpPresenter by moxyPresenter { presenterProvider.get() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userName.doOnTextChanged { text, start, before, count ->
            binding.tiUserName
        }

        binding.btnSingUp.setOnClickListener {
            with(binding) {
                presenter.save(
                    userName.text.toString(),
                    birthday.text.toString(),
                    phoneNumber.text.toString(),
                    email.text.toString(),
                    password.text.toString(),
                )
            }

            //findNavController().navigate(R.id.action_singUpFragment_to_mainFragment)
        }

        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun render(state: SingInState) = with(binding) {
        when (state) {
            is SingInState.UserNameField -> {
                Log.e("base", "Имя уже занято")
                tiUserName.error = getString(R.string.name_already_taken)
                //etUserName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0)
            }

            is SingInState.BirthdayField -> {}
            is SingInState.PhoneField -> {}
            is SingInState.EmailField -> {}
            is SingInState.PasswordField -> {}
            is SingInState.ConfirmPasswordField -> {}
        }
    }
}