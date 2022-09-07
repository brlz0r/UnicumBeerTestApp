package ru.kiruxadance.unicumbeertestapp.beer.presentation.beers

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import ru.kiruxadance.unicumbeertestapp.beer.presentation.beers.components.BeerItem

@Composable
fun BeersScreen(
    navController: NavController,
    viewModel: BeersViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is BeersViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Crossfade(targetState = state) {
                when(it.beerListState) {
                    BeerListState.List -> {
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 12.dp)) {
                            items(state.beers) { beer ->
                                BeerItem(
                                    beer = beer,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.onEvent(BeerEvent.ClickedBeer(beer))
                                        }
                                        .background(Color.White),
                                )
                            }
                        }
                    }
                    BeerListState.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}