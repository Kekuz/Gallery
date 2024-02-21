package com.gallery.ui.fragment_sing_up

sealed interface SingUpState{
    data class UserNameField(val error: String?): SingUpState
    data class BirthdayField(val error: String?): SingUpState
    data class PhoneField(val error: String?): SingUpState
    data class EmailField(val error: String?): SingUpState
    data class PasswordField(val error: String?): SingUpState
    data class ConfirmPasswordField(val error: String?): SingUpState
}
