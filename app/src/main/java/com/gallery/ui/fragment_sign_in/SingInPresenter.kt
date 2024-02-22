package com.gallery.ui.fragment_sign_in

import com.gallery.functional.database.DatabaseRepository
import com.gallery.functional.database.model.User
import com.gallery.functional.shared_prefs.SharedPrefsAuthSaveStorage
import com.gallery.functional.validate.ValidateLoginInInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject

class SingInPresenter @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val validateLoginInInteractor: ValidateLoginInInteractor,
    private val sharedPrefsAuthSaveStorage: SharedPrefsAuthSaveStorage,
) : MvpPresenter<SingInView>() {

    private var currentUser: User? = null

    suspend fun loginIn(
        email: String,
        password: String,
    ) {
        if (validate(email, password)) {
            getUser(email)
            saveAuth()
            navigateToMain()
        }
    }

    private fun saveAuth() {
        currentUser?.let { sharedPrefsAuthSaveStorage.saveUser(it) }
    }

    private suspend fun validate(
        email: String,
        password: String,
    ): Boolean = withContext(Dispatchers.Main) {

        val emailError = validateLoginInInteractor.validateLogin(email)
        val passwordError = validateLoginInInteractor.validatePassword(email, password)


        viewState.renderFieldError(SingInState.EmailField(emailError))
        viewState.renderFieldError(SingInState.PasswordField(passwordError))

        return@withContext (emailError == null) && (passwordError == null)
    }

    private suspend fun getUser(email: String) {
        currentUser = databaseRepository.getUser(email)
    }

    fun navigateBack() {
        viewState.navigateBack()
    }

    fun navigateToSingUp() {
        viewState.navigateToSingUp()
    }

    private fun navigateToMain() {
        viewState.navigateToMain()
    }
}