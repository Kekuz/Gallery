package com.gallery.ui.fragment_sing_up

import com.gallery.database.DatabaseRepository
import com.gallery.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject


class SingUpPresenter @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) : MvpPresenter<SingUpView>() {

    fun save(
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
        ) {
            viewState.render(SingInState.UserNameField)
        }
    }

}