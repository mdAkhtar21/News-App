package com.example.newsapp.Domain.usecases.app_entry

import com.example.newsapp.Domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager:LocalUserManager) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}