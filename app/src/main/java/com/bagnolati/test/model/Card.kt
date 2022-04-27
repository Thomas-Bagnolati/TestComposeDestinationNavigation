package com.bagnolati.test.model

import kotlin.random.Random

data class Card(
    val id: Int = Random.nextInt(10_000),
    val title: String,
    val desc: String,
)