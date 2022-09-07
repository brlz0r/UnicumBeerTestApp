package ru.kiruxadance.unicumbeertestapp.beer.presentation.beers

import ru.kiruxadance.unicumbeertestapp.beer.domain.model.Beer

data class BeersState(
    val beers: List<Beer> = emptyList(),
    val beerListState: BeerListState = BeerListState.Loading
)
