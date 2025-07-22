package com.example.newsapp.Data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.Domain.manager.LocalUserManager
import com.example.newsapp.utill.Constants
import com.example.newsapp.utill.Constants.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImp(private val context:Context):LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { setting->
            setting[PreferencesKeys.APP_ENTRY]=true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
       return context.dataStore.data.map {preferences->
           preferences[PreferencesKeys.APP_ENTRY]?:false

        }
    }
}
private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)

private object PreferencesKeys{
    val APP_ENTRY= booleanPreferencesKey(name = Constants.APP_ENTRY)
}