package com.gallery.ui.fragment_sign_in

import com.gallery.database.DatabaseRepository
import com.gallery.database.model.User
import com.gallery.validate.ValidateLoginInInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject

class SingInPresenter @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val validateLoginInInteractor: ValidateLoginInInteractor,
) : MvpPresenter<SingInView>() {

    private var currentUser: User? = null

    suspend fun loginIn(
        email: String,
        password: String,
    ): Boolean {
        val success = validate(email, password)
        if (success) {
            getUser(email)
        }
        return success
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
        currentUser = databaseRepository.getUser(
            email,
        )
    }
}