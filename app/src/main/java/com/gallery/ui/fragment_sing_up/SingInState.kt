package com.gallery.ui.fragment_sing_up

sealed interface SingInState{
    data class UserNameField(val error: String?): SingInState
    data class BirthdayField(val error: String?): SingInState
    data class PhoneField(val error: String?): SingInState
    data class EmailField(val error: String?): SingInState
    data class PasswordField(val error: String?): SingInState
    data class ConfirmPasswordField(val error: String?): SingInState
}
