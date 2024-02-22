package com.gallery.ui.fragment_settings

import com.gallery.functional.shared_prefs.SharedPrefsAuthSaveStorage
import moxy.MvpPresenter
import javax.inject.Inject

class SettingsPresenter @Inject constructor(
   private val sharedPrefsAuthSaveStorage: SharedPrefsAuthSaveStorage
) : MvpPresenter<SettingsView>() {

    fun singOut() {
        sharedPrefsAuthSaveStorage.clear()
        viewState.navigateSingOut()
    }

    fun navigateBack(){
        viewState.navigateBack()
    }

    fun navigateBackWithSave(){
        viewState.navigateBackWithSave()
    }
}