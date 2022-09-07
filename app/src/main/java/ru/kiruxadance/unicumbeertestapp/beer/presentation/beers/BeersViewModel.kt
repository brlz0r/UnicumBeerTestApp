package ru.kiruxadance.unicumbeertestapp.beer.presentation.beers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kiruxadance.unicumbeertestapp.beer.domain.use_case.BeerUseCases
import ru.kiruxadance.unicumbeertestapp.common.Resource
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(
    private val beerUseCases: BeerUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BeersState())
    val state: State<BeersState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getBeers()
    }

    fun onEvent(event: BeerEvent) {
        when(event) {
            is BeerEvent.ClickedBeer -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.ShowSnackbar("You clicked to " + event.beer.name))
                }
            }
        }
    }


    private fun getBeers() {
        viewModelScope.launch {
            beerUseCases.getBeers().collectLatest {
                when(it) {
                    is Resource.Error -> {
                        _eventFlow.emit(UiEvent.ShowSnackbar(it.message ?: "An unexpected error occured"))
                        _state.value = _state.value.copy(
                            beerListState = BeerListState.List
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            beerListState = BeerListState.Loading
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            beers = it.data ?: emptyList(),
                            beerListState = BeerListState.List
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
    }
}