package com.bagnolati.test.presentation.screens.bottombar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bagnolati.test.R
import com.bagnolati.test.navigation.BottomBarNavGraph
import com.bagnolati.test.navigation.MainNavGraph
import com.bagnolati.test.presentation.screens.NavGraphs
import com.bagnolati.test.presentation.screens.appDestination
import com.bagnolati.test.presentation.screens.destinations.*
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.spec.NavHostEngine


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
@MainNavGraph(start = true)
@Destination
fun BottomBarScreen(
) {
    val navHostEngine = rememberAnimatedNavHostEngine()
    val navController = navHostEngine.rememberNavController()
    Scaffold(
        topBar = {

        },
        bottomBar = {
            BottomNavigationBar(navController)
        })
    { paddingValues ->
        Navigation(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            engine = navHostEngine
        )
    }

}

@BottomBarNavGraph(true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "HomeScreen")
}

@Destination()
@BottomBarNavGraph
@Composable
fun MusicScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "MusicScreen")
}

@Destination()
@BottomBarNavGraph
@Composable
fun MoviesScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "MoviesScreen")
}

@Destination()
@BottomBarNavGraph
@Composable
fun BooksScreen(
    navigator: DestinationsNavigator,
) {
    Column {
        Text(text = "BooksScreen")

        Button(
            onClick = { navigator.navigate(CardListScreenDestination) }
        ) {
            Text("Go to CardListScreen")
        }
    }

}


@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.appDestination(NavGraphs.bottomBar)
        NavigationItem.values().forEach { item ->
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
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    engine: NavHostEngine,
) {
    DestinationsNavHost(
        modifier = modifier,
        engine = engine,
        navController = navController,
        navGraph = NavGraphs.bottomBar,
    )
}

enum class NavigationItem(val direction: DirectionDestinationSpec, val icon: Int, val title: String) {
    Home(HomeScreenDestination, R.drawable.ic_profil, "Home"),
    Music(MusicScreenDestination, R.drawable.ic_profil, "Music"),
    Movies(MoviesScreenDestination, R.drawable.ic_profil, "Movies"),
    Books(BooksScreenDestination, R.drawable.ic_profil, "Books"),
}
