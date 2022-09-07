package ru.kiruxadance.unicumbeertestapp.beer.presentation.beers

import ru.kiruxadance.unicumbeertestapp.beer.domain.model.Beer

sealed class BeerEvent {
    data class ClickedBeer(val beer: Beer): BeerEvent()
}