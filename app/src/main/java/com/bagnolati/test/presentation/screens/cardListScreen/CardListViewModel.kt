package com.bagnolati.test.presentation.screens.cardListScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bagnolati.test.model.Card
import com.bagnolati.test.utils.setWhenFirst
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CardListViewModel @Inject constructor(
) : ViewModel() {

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> = _cards

    init {
        _cards.add(
            Card(id = 1, title = "title", desc = "something")
        )
    }

    fun addCard() {
        _cards.add(
            Card(title = "Card", desc = "Click change name")
        )
    }

    fun removeLastCard() {
        val size = _cards.size
        if (_cards.isNotEmpty()) {
            _cards.removeAt(size - 1)
        }
    }

    fun changeTitleCard(card: Card) {
        val random = Random.nextInt(0, 100)
        _cards.setWhenFirst(
            predicate = { it.id == card.id },
            element = card.copy(title = "Working so well !! $random"))
    }

}