package com.example.news.auth.model.repository

import com.example.news.auth.model.source.local.FakeAuthLocalDataSource
import com.example.news.auth.model.source.remote.FakeAuthWebservice
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class AuthRepositoryImplTest {

    lateinit var authRepo: AuthRepository
    lateinit var authLocalDataSource: FakeAuthLocalDataSource
    lateinit var authWebservice: FakeAuthWebservice
    lateinit var email: String
    lateinit var name: String
    lateinit var password: String
    @Before
    fun init(){
        authLocalDataSource = FakeAuthLocalDataSource()
        authWebservice = FakeAuthWebservice()
//        authRepo = AuthRepositoryImpl(authWebservice, authLocalDataSource)
        email = "rafeef@gmail.com"
        name = "rafeef"
        password = "123456"
    }
    @Test
    fun login_checkUserExistence_TrueIfUserExist() = runBlockingTest {
        // given name, email

        // when calling the login method
        authRepo.login(email, password)
        // check user

    }

    @Test
    fun logout() {
        // given nothing

        // when calling the logout method
//        authRepo.logout()
    }

    @Test
    fun signUp_checkIfUserExist_assertTrue() = runBlockingTest {
        // email, name, password

        // when calling the signUp method
        authRepo.signUp(email, password, name)

        // assert
    }

    @Test
    fun saveUserData_checkUserData_userDataIsTrue() {
        // given email, name, id
        var id : Int = 1
        // when calling the saveUserData method
//        authRepo.saveUserData(id, email, name)

    }
}