package com.example.funquizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.funquizapp.ui.screens.DetailScreen
import com.example.funquizapp.ui.screens.HomeScreen
import com.example.funquizapp.ui.theme.FunQuizAppTheme
import com.example.funquizapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            FunQuizAppTheme {
                NavigateCompose()

            }
        }
    }
}


@Composable
fun NavigateCompose() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") {
            HomeScreen(
                navController
            )
        }
        composable(
            route = "screen2/{myArg}",
            arguments = listOf(navArgument("myArg") { type = NavType.StringType })
        ) { navBackStackEntry ->


            val myArg = navBackStackEntry.arguments?.getString("myArg")
            DetailScreen(
                navController, myArg
            )
        }
    }
}