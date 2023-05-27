package com.example.news.auth.ui.registration

sealed interface RegisterUIEvent {
    class NavigateToLogin(val email : String) : RegisterUIEvent
}