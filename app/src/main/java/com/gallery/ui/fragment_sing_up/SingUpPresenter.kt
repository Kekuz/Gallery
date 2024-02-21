package com.gallery.ui.fragment_sing_up

import com.gallery.database.DatabaseRepository
import com.gallery.database.model.User
import com.gallery.validate.ValidateInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject


class SingUpPresenter @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val validateInteractor: ValidateInteractor,
) : MvpPresenter<SingUpView>() {

    fun register(
        userName: String,
        birthday: String,
        phoneNumber: String,
        email: String,
        password: String,
    ) {
        databaseRepository.saveUser(
            User(
                userName,
                birthday,
                phoneNumber,
                email,
                password,
            )
        )
    }

    suspend fun validate(
        userName: String,
        birthday: String,
        phoneNumber: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Boolean = withContext(Dispatchers.Main) {

        val userNameError = validateInteractor.validateUserName(userName)
        val birthdayError = validateInteractor.validateBirthday(birthday)
        val phoneNumberError = validateInteractor.validatePhoneNumber(phoneNumber)
        val emailError = validateInteractor.validateEmail(email)
        val passwordError = validateInteractor.validatePassword(password)
        val confirmPasswordError = validateInteractor.validateConfirmPassword(password, confirmPassword)

        viewState.renderFieldError(SingInState.UserNameField(userNameError))
        viewState.renderFieldError(SingInState.BirthdayField(birthdayError))
        viewState.renderFieldError(SingInState.PhoneField(phoneNumberError))
        viewState.renderFieldError(SingInState.EmailField(emailError))
        viewState.renderFieldError(SingInState.PasswordField(passwordError))
        viewState.renderFieldError(SingInState.ConfirmPasswordField(confirmPasswordError))


        return@withContext userNameError == null
                && birthdayError == null
                && phoneNumberError == null
                && emailError == null
                && passwordError == null
                && confirmPasswordError == null
    }


}