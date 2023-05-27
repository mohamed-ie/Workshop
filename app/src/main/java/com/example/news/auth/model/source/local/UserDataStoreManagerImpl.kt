package com.example.news.auth.model.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.news.helpers.DataStoreKeys
import kotlinx.coroutines.flow.map

class UserDataStoreManagerImpl(private val dataStore: DataStore<Preferences>) : UserDataStoreManager{

    override suspend fun saveUserData(userData: UserData) {
        dataStore.edit {
            it[intPreferencesKey(DataStoreKeys.ID)] = userData.userID ?: -1
            it[stringPreferencesKey(DataStoreKeys.NAME)] = userData.name ?: ""
            it[stringPreferencesKey(DataStoreKeys.EMAIL)] = userData.email ?: ""
        }
    }

    override suspend fun resetUserData() {
        dataStore.edit {
            it[intPreferencesKey(DataStoreKeys.ID)] = -1
            it[stringPreferencesKey(DataStoreKeys.NAME)] = ""
            it[stringPreferencesKey(DataStoreKeys.EMAIL)] = ""
        }
    }

    override suspend fun getUserDataStore() = dataStore.data.map {
        it[intPreferencesKey(DataStoreKeys.ID)]
    }
}

data class UserData(
    val userID: Int? = null,
    val name:String? = null,
    val email:String? = null
)