package com.gallery.ui.fragment_sing_up

sealed interface SingInState{
    data object UserNameField: SingInState
    data object BirthdayField: SingInState
    data object PhoneField: SingInState
    data object EmailField: SingInState
    data object PasswordField: SingInState
    data object ConfirmPasswordField: SingInState
}
