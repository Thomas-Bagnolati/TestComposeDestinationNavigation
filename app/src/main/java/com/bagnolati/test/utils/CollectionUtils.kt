package com.bagnolati.test.utils


fun <T> MutableList<T>.setWhenFirst(element: T, predicate: (T) -> Boolean) {
    val index = this.indexOfFirst(predicate)
    this.set(index, element)
}