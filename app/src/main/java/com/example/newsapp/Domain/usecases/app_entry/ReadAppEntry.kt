package com.example.newsapp.Domain.usecases.app_entry

import com.example.newsapp.Domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {
    operator fun invoke(): Flow<Boolean>{
      return  localUserManager.readAppEntry()
    }
}