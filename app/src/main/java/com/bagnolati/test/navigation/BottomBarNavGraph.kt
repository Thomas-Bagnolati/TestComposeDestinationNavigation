package com.bagnolati.test.navigation

import com.ramcosta.composedestinations.annotation.NavGraph


@NavGraph(default = true)
annotation class MainNavGraph(val start: Boolean = false)

@NavGraph
annotation class BottomBarNavGraph(val start: Boolean = false)
