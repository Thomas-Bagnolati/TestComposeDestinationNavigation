package com.bagnolati.test.presentation.screens.cardListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bagnolati.test.model.Card
import com.bagnolati.test.navigation.DestinationAnimationStyle
import com.bagnolati.test.navigation.MainNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@Destination(style = DestinationAnimationStyle::class)
@MainNavGraph
@Composable
fun CardListScreen(
    viewModel: CardListViewModel = hiltViewModel(),
) {

    val cards = viewModel.cards

    val onClickCard: (card: Card) -> Unit = { card ->
        viewModel.changeTitleCard(card = card)
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {

                items(items = cards) { card ->
                    ItemCard(
                        onClickCard = onClickCard,
                        card = card
                    )

                }
            }
        )

        Column(
            modifier = Modifier.padding(end = 14.dp),
            horizontalAlignment = Alignment.End) {
            Button(onClick = { viewModel.addCard() }) {
                Text(text = "Add Card")
            }
            Button(onClick = { viewModel.removeLastCard() }) {
                Text(text = "Delete last Card")
            }
        }
    }
}


@Composable
fun ItemCard(
    onClickCard: (card: Card) -> Unit,
    card: Card,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.LightGray)
            .clickable { onClickCard(card) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = card.title, style = MaterialTheme.typography.subtitle1)
        Text(text = card.desc)
    }
}