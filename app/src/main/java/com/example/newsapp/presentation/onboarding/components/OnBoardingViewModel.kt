package com.example.newsapp.presentation.onboarding.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Domain.usecases.app_entry.AppEntryUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor
    (private val appEntryUsecases: AppEntryUsecases) : ViewModel() {
        fun onEvent(event: OnBoardingEvent){
            when(event){
                is OnBoardingEvent.SaveAppEntry ->{
                    saveAppEntry()
                }

            }
        }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUsecases.saveAppEntry()
        }
    }
}