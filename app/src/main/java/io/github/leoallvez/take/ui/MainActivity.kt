package io.github.leoallvez.take.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.leoallvez.take.ui.home.HomeScreen
import io.github.leoallvez.take.ui.home.HomeViewModel
import io.github.leoallvez.take.ui.splash.SplashViewModel
import io.github.leoallvez.take.ui.splash.SplashScreen
import io.github.leoallvez.take.ui.theme.TakeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TakeApp()
                }
            }
        }
    }
}

@Composable
fun TakeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen(navController)
        }
        composable(route = Screen.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel)
        }
    }
}