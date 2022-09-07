package ru.kiruxadance.unicumbeertestapp.beer.data.data_source

import retrofit2.http.GET
import ru.kiruxadance.unicumbeertestapp.beer.domain.model.Beer

interface BeerService {
    @GET("beers")
    suspend fun getBeers() : List<Beer>
}