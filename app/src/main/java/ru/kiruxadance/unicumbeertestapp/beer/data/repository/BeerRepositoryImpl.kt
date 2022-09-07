package ru.kiruxadance.unicumbeertestapp.beer.data.repository

import ru.kiruxadance.unicumbeertestapp.beer.data.data_source.BeerService
import ru.kiruxadance.unicumbeertestapp.beer.domain.model.Beer
import ru.kiruxadance.unicumbeertestapp.beer.domain.repository.BeerRepository

class BeerRepositoryImpl(
    private val beerService: BeerService
) : BeerRepository {

    override suspend fun getBeers(): List<Beer> {
        return beerService.getBeers()
    }
}