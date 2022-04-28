package com.bagnolati.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import com.bagnolati.test.presentation.screens.NavGraphs
import com.bagnolati.test.presentation.theme.TestTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostEngine = rememberAnimatedNavHostEngine()
            TestTheme {
                Surface(color = Color.White) {
                    DestinationsNavHost(navGraph = NavGraphs.main, engine = navHostEngine)
                }

            }
        }
    }
}