package ru.kiruxadance.unicumbeertestapp.beer.domain.repository

import ru.kiruxadance.unicumbeertestapp.beer.domain.model.Beer

interface BeerRepository {
    suspend fun getBeers(): List<Beer>
}