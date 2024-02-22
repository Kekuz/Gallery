package com.gallery.ui.fragment_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentProfileBinding
import com.gallery.ui.mockup.MockupPictures
import com.gallery.ui.fragment_profile.recycler.ProfilePictureAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class ProfileFragment : MvpAppCompatFragment(), ProfileView {
    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var presenterProvider: Provider<ProfilePresenter>

    private val presenter: ProfilePresenter by moxyPresenter { presenterProvider.get() }

    private val adapter = ProfilePictureAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setNameAndBirthdayFields()

        bindRecycler()
        bindSettingsButton()

    }

    private fun bindSettingsButton() = with(binding) {
        iconSettings.setOnClickListener {
            presenter.navigateToSettings()
        }
    }

    private fun bindRecycler() = with(binding) {
        recycler.adapter = adapter
        progressBar.isVisible = true
        lifecycleScope.launch {
            delay(2000)
            adapter.add(MockupPictures.get())
            withContext(Dispatchers.Main) {
                progressBar.isVisible = false
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun loadUserData(userName: String, birthday: String) = with(binding) {
        tvUsername.text = userName
        tvUsername.isVisible = true

        tvBirthday.text = birthday
        tvBirthday.isVisible = true
    }

    override fun navigateToSettings() {
        findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
    }

}