package com.gallery.ui.fragment_profile

import com.gallery.functional.shared_prefs.SharedPrefsAuthSaveStorage
import moxy.MvpPresenter
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    sharedPrefsAuthSaveStorage: SharedPrefsAuthSaveStorage
) : MvpPresenter<ProfileView>() {

    private var currentUser = sharedPrefsAuthSaveStorage.getUser()!!
    fun setFields() {
        viewState.render(ProfileState.UserData(currentUser.userName, currentUser.birthday))
    }
}