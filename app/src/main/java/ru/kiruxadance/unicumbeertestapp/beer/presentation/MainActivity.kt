package ru.kiruxadance.unicumbeertestapp.beer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.kiruxadance.unicumbeertestapp.R
import ru.kiruxadance.unicumbeertestapp.beer.presentation.beers.BeersScreen
import ru.kiruxadance.unicumbeertestapp.beer.presentation.util.Screen
import ru.kiruxadance.unicumbeertestapp.theme.BeerAppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerAppTheme {
                Surface(
                    color = MaterialTheme.colors.primary
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.BeersScreen.route
                    ) {
                        composable(route = Screen.BeersScreen.route) {
                            BeersScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}