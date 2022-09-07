package ru.kiruxadance.unicumbeertestapp.beer.presentation.beers

sealed class BeerListState {
    object List: BeerListState()
    object Loading: BeerListState()
}