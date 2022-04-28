package com.bagnolati.test.navigation

import com.ramcosta.composedestinations.annotation.NavGraph


@NavGraph(default = true)
annotation class MainNavGraph(val start: Boolean = false)

@NavGraph(default = false)
annotation class BottomBarNavGraph(val start: Boolean = false)
