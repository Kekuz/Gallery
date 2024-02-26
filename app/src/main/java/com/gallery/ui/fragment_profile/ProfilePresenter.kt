package com.gallery.ui.fragment_profile

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.gallery.functional.shared_prefs.SharedPrefsAuthSaveStorage
import com.gallery.ui.mockup.MockupPictures
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    sharedPrefsAuthSaveStorage: SharedPrefsAuthSaveStorage
) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.addMockupPhotos(MockupPictures.get())
    }


    private var currentUser = sharedPrefsAuthSaveStorage.getUser()!!
    fun setNameAndBirthdayFields() {
        viewState.loadUserData(currentUser.userName, currentUser.birthday)
    }

    fun navigateToSettings() {
        viewState.navigateToSettings()
    }
}