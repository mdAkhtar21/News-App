package com.example.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapp.Domain.usecases.app_entry.AppEntryUsecases
import com.example.newsapp.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUsecases: AppEntryUsecases
) :ViewModel(){


    var _splashScreenCondition by mutableStateOf(true)
        private set

    var _startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUsecases.readAppEntry().onEach { shouldStartFromHome->
            if(shouldStartFromHome){
                _startDestination=Route.NewsNavigation.route
            }else{
                _startDestination=Route.AppStartNavigation.route
            }
            delay(3000)
            _splashScreenCondition=false
        }
            .launchIn(viewModelScope)
    }

}