package ru.kiruxadance.unicumbeertestapp.beer.presentation.util

sealed class Screen(val route: String) {
    object BeersScreen: Screen("beers_screen")
}