package ru.kiruxadance.unicumbeertestapp.beer.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.kiruxadance.unicumbeertestapp.beer.domain.model.Beer
import ru.kiruxadance.unicumbeertestapp.beer.domain.repository.BeerRepository
import ru.kiruxadance.unicumbeertestapp.common.Resource
import java.io.IOException

class GetBeers(
    private val repository: BeerRepository
){
    operator fun invoke(): Flow<Resource<List<Beer>>> = flow {
        try {
            emit(Resource.Loading())
            val beers = repository.getBeers()
            emit(Resource.Success(beers))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}