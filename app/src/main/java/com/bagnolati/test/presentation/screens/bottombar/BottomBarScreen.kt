package com.bagnolati.test.presentation.screens.bottombar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bagnolati.test.R
import com.bagnolati.test.common.DestinationAnimationStyle
import com.bagnolati.test.presentation.screens.NavGraphs
import com.bagnolati.test.presentation.screens.appDestination
import com.bagnolati.test.presentation.screens.destinations.BooksScreenDestination
import com.bagnolati.test.presentation.screens.destinations.HomeScreenDestination
import com.bagnolati.test.presentation.screens.destinations.MoviesScreenDestination
import com.bagnolati.test.presentation.screens.destinations.MusicScreenDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.spec.NavHostEngine


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@RootNavGraph(start = true)
@Destination(style = DestinationAnimationStyle::class)
@Composable
fun BottomBarScreen(
    navigator: DestinationsNavigator,
) {
    val navController = rememberNavController()
    val nasHostEngine = rememberAnimatedNavHostEngine()
    Scaffold(
        topBar = {

        },
        bottomBar = {
            BottomNavigationBar(navController)
        })
    { paddingValues ->
        paddingValues
        Navigation(navController, engine = nasHostEngine, navigator = navigator)
    }

}

@Destination()
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "HomeScreen")
}

@Destination()
@Composable
fun MusicScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "MusicScreen")
}

@Destination()
@Composable
fun MoviesScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "MoviesScreen")
}

@Destination()
@Composable
fun BooksScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "BooksScreen")
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Music,
        NavigationItem.Movies,
        NavigationItem.Books,
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.appDestination()
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentDestination == item.direction,
                onClick = {
                    navController.navigateTo(item.direction) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun Navigation(navController: NavHostController, engine: NavHostEngine, navigator: DestinationsNavigator) {
    DestinationsNavHost(navController = navController, startRoute = NavigationItem.Home.direction, navGraph = NavGraphs.root, engine = engine) {
        composable(NavigationItem.Home.direction) {
            HomeScreen(navigator)
        }
        composable(NavigationItem.Music.direction) {
            MusicScreen(navigator)
        }
        composable(NavigationItem.Movies.direction) {
            MoviesScreen(navigator)
        }
        composable(NavigationItem.Books.direction) {
            BooksScreen(navigator)
        }
    }
}

sealed class NavigationItem(val direction: DirectionDestinationSpec, var icon: Int, var title: String) {
    object Home : NavigationItem(HomeScreenDestination, R.drawable.ic_profil, "Home")
    object Music : NavigationItem(MusicScreenDestination, R.drawable.ic_profil, "Music")
    object Movies : NavigationItem(MoviesScreenDestination, R.drawable.ic_profil, "Movies")
    object Books : NavigationItem(BooksScreenDestination, R.drawable.ic_profil, "Books")
}
