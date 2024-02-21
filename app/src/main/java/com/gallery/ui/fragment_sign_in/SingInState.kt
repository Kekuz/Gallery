package com.gallery.ui.fragment_sign_in

sealed interface SingInState{
    data class EmailField(val error: String?): SingInState
    data class PasswordField(val error: String?): SingInState
}
