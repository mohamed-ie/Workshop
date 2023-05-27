@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.news.auth.ui.registration

import com.example.news.MainCoroutineRule
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegistrationViewModelTest {
    private lateinit var viewModel: RegistrationViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        val repository = FakeAuthRepository()
        viewModel = RegistrationViewModel(repository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun register_noInputs_DisplayNameAndEmailAndPasswordEmptyError() {
        //given
        val passwordError = viewModel.passwordError
        val emailError = viewModel.emailError
        val displayNameError = viewModel.displayNameError

        //when
        viewModel.register()

        //then
        assertNotNull(displayNameError.value)
        assertNotNull(passwordError.value)
        assertNotNull(emailError.value)
    }

    @Test
    fun register_validInputs_uiEventNavigateToLogin() = runTest {
        //given
        val uiEvent = viewModel.uiEvent
        viewModel.email.update { "mohammedie98@gmail.com" }
        viewModel.password.update { "123456789" }
        viewModel.displayName.update { "mohamed" }


        //when
        val registerJob = launch(start = CoroutineStart.LAZY) {
            viewModel.register()
        }

        //then
        val assertJob = launch(start = CoroutineStart.LAZY) {
            val result = uiEvent.first()
            assertTrue(result is RegisterUIEvent.NavigateToLogin)
        }

        assertJob.start()
        registerJob.start()
    }

    @Test
    fun register_validInputsExistEmail_uiEventNavigateToLogin() = runTest {
        //given
        val remoteError = viewModel.remoteError
        viewModel.email.update { "mohammedie98@gmail.com" }
        viewModel.password.update { "123456789" }
        viewModel.displayName.update { "mohamed" }


        //when
        viewModel.register()
        viewModel.register()

        //then
        val expected = ""
        val result = remoteError.value
        assertNotEquals(expected, result)

    }
}