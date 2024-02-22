package com.gallery.ui.fragment_profile


sealed interface ProfileState {
    data class UserData(val userName: String, val birthday: String) : ProfileState

}