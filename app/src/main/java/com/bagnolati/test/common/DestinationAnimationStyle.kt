package com.bagnolati.test.common

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.bagnolati.test.presentation.screens.appDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object DestinationAnimationStyle : DestinationStyle.Animated {

    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return when (initialState.appDestination()) {
            else -> SlideInOpen
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return when (targetState.appDestination()) {
            else -> SlideOutClose
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return when (initialState.appDestination()) {
            else -> SlideInClose
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return when (targetState.appDestination()) {
            else -> SlideOutOpen
        }
    }
}

const val EXTRA_SHORT = 200

val SlideInOpen: EnterTransition = slideInHorizontally(animationSpec = tween(EXTRA_SHORT), initialOffsetX = { it })
val SlideOutClose: ExitTransition = slideOutHorizontally(animationSpec = tween(EXTRA_SHORT), targetOffsetX = { -it })
val SlideInClose: EnterTransition = slideInHorizontally(animationSpec = tween(EXTRA_SHORT), initialOffsetX = { -it })
val SlideOutOpen: ExitTransition = slideOutHorizontally(animationSpec = tween(EXTRA_SHORT), targetOffsetX = { it })
