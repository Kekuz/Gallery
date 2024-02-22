package com.gallery.ui.fragment_welcome

import com.gallery.functional.shared_prefs.SharedPrefsAuthSaveStorage
import moxy.MvpPresenter
import javax.inject.Inject

class WelcomePresenter @Inject constructor(
    sharedPrefsAuthSaveStorage: SharedPrefsAuthSaveStorage,
) : MvpPresenter<WelcomeView>() {
    private var currentUser = sharedPrefsAuthSaveStorage.getUser()

    fun checkAuth(){
        if(currentUser != null){
            viewState.navigateAuth()
        }
    }
}