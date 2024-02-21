package com.gallery.ui.fragment_sing_up

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentSingUpBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class SingUpFragment : MvpAppCompatFragment(), SingUpView {

    private lateinit var binding: FragmentSingUpBinding

    @Inject
    lateinit var presenterProvider: Provider<SingUpPresenter>

    private val presenter: SingUpPresenter by moxyPresenter { presenterProvider.get() }

    private val calendar = Calendar.getInstance()


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
            with(binding) {
                val userName = userName.text.toString()
                val birthday = birthday.text.toString()
                val phoneNumber = phoneNumber.text.toString()
                val email = email.text.toString()
                val password = password.text.toString()
                val confirmPassword = confirmPassword.text.toString()

                lifecycleScope.launch {
                    if (presenter.validate(
                            userName,
                            birthday,
                            phoneNumber,
                            email,
                            password,
                            confirmPassword
                        )
                    ) {
                        presenter.register(
                            userName,
                            birthday,
                            phoneNumber,
                            email,
                            password,
                        )
                        findNavController().navigate(R.id.action_singUpFragment_to_mainFragment)
                    }
                }
            }
        }
        binding.birthday.inputType = InputType.TYPE_NULL

        binding.birthday.setOnClickListener {
            showDatePicker()
            hideKeyboard(it)
        }

        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        clearErrorOnInputPassword()
    }

    override fun renderFieldError(state: SingInState) = with(binding) {
        when (state) {
            is SingInState.UserNameField -> {
                tiUserName.error = state.error
            }

            is SingInState.BirthdayField -> {
                tiBirthday.error = state.error
            }

            is SingInState.PhoneField -> {
                tiPhoneNumber.error = state.error
            }

            is SingInState.EmailField -> {
                tiEmail.error = state.error
            }

            is SingInState.PasswordField -> {
                tiPassword.error = state.error
            }

            is SingInState.ConfirmPasswordField -> {
                tiConfirmPassword.error = state.error
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun clearErrorOnInputPassword() {
        binding.password.doOnTextChanged { _, _, _, _ ->
            binding.tiPassword.error = null
        }

        binding.confirmPassword.doOnTextChanged { _, _, _, _ ->
            binding.tiConfirmPassword.error = null
        }
    }

    private fun showDatePicker(){
        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .setTheme(R.style.MyCalendar)
            .setTitleText(getString(R.string.enter_birthday_date))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        materialDatePicker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(it)
            binding.birthday.setText(formattedDate)
        }
        materialDatePicker.show(this.parentFragmentManager, "")
    }
}