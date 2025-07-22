package com.example.newsapp

import android.os.Bundle
import android.view.WindowInsets.Side
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.Data.local.NewsDao
import com.example.newsapp.Domain.model.Article
import com.example.newsapp.Domain.model.Source
import com.example.newsapp.presentation.nvgraph.AppNavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel._splashScreenCondition
            }
        }
        setContent {
            val isSystemInDarkMode = isSystemInDarkTheme()
            val systemController = rememberSystemUiController()

            SideEffect {
                systemController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !isSystemInDarkMode
                )
            }
        }
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                    val startDestination=viewModel._startDestination
                    AppNavGraph(startDestination=startDestination)
                }

            }
        }
    }
}

