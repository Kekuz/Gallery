package com.gallery.ui.fragment_settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class SettingsFragment : MvpAppCompatFragment(), SettingsView {

    private lateinit var binding: FragmentSettingsBinding

    @Inject
    lateinit var presenterProvider: Provider<SettingsPresenter>

    private val presenter: SettingsPresenter by moxyPresenter { presenterProvider.get() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.renderFields()
        bindButtons()
    }

    private fun bindButtons() = with(binding) {
        btnCancel.setOnClickListener {
            presenter.navigateBack()
        }

        btnSave.setOnClickListener {
            presenter.navigateBackWithSave()
        }

        tvSingOut.setOnClickListener {
            presenter.singOut()
        }
    }

    override fun navigateSingOut() {
        findNavController().navigate(R.id.action_global_welcomeFragment)
    }

    override fun navigateBack() {
        findNavController().navigateUp()
    }

    override fun navigateBackWithSave() {
        findNavController().navigateUp()
    }

    override fun renderFields(
        userName: String,
        birthday: String,
        email: String,
        phoneNumber: String
    ) = with(binding) {
        etUserName.setText(userName)
        etBirthday.setText(birthday)
        etEmail.setText(email)
        etPhoneNumber.setText(phoneNumber)
    }
}