package com.gallery.ui.fragment_sing_up

import com.gallery.functional.database.DatabaseRepository
import com.gallery.functional.database.model.User
import com.gallery.functional.shared_prefs.SharedPrefsAuthSaveStorage
import com.gallery.functional.validate.ValidateRegisterInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject


class SingUpPresenter @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val validateRegisterInteractor: ValidateRegisterInteractor,
    private val sharedPrefsAuthSaveStorage: SharedPrefsAuthSaveStorage,
) : MvpPresenter<SingUpView>() {

    private fun navigateToMain() {
        viewState.navigateToMain()
    }

    fun navigateSingIn() {
        viewState.navigateToSingIn()
    }

    fun navigateToBack() {
        viewState.navigateBack()
    }

    suspend fun register(
        userName: String,
        birthday: String,
        phoneNumber: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        if (validate(userName, birthday, phoneNumber, email, password, confirmPassword)) {
            val user = User(
                userName,
                birthday,
                phoneNumber,
                email,
                password,
            )
            databaseRepository.saveUser(user)
            sharedPrefsAuthSaveStorage.saveUser(user)
            navigateToMain()
        }
    }

    private suspend fun validate(
        userName: String,
        birthday: String,
        phoneNumber: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Boolean = withContext(Dispatchers.Main) {

        val userNameError = validateRegisterInteractor.validateUserName(userName)
        val birthdayError = validateRegisterInteractor.validateBirthday(birthday)
        val phoneNumberError = validateRegisterInteractor.validatePhoneNumber(phoneNumber)
        val emailError = validateRegisterInteractor.validateEmail(email)
        val passwordError = validateRegisterInteractor.validatePassword(password)
        val confirmPasswordError =
            validateRegisterInteractor.validateConfirmPassword(password, confirmPassword)

        viewState.renderFieldError(SingUpState.UserNameField(userNameError))
        viewState.renderFieldError(SingUpState.BirthdayField(birthdayError))
        viewState.renderFieldError(SingUpState.PhoneField(phoneNumberError))
        viewState.renderFieldError(SingUpState.EmailField(emailError))
        viewState.renderFieldError(SingUpState.PasswordField(passwordError))
        viewState.renderFieldError(SingUpState.ConfirmPasswordField(confirmPasswordError))


        return@withContext userNameError == null
                && birthdayError == null
                && phoneNumberError == null
                && emailError == null
                && passwordError == null
                && confirmPasswordError == null
    }


}