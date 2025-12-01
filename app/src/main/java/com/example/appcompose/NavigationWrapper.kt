package com.example.appcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost( navController = navController, startDestination=Login){
        composable<Login>{
            LoginScreen{ navController.navigate(Searcher) }
        }
        composable<Searcher>{
            SearcherScreen{ name -> navController.navigate(Profile(name = name)) }
        }
        composable<Profile>{ backStackEntry ->
            val profile:Profile = backStackEntry.toRoute()
            ProfileScreen(profile.name) { navController.popBackStack()}
        }
        composable<Detail>{ backStackEntry ->
            val detail:Detail = backStackEntry.toRoute()
            DetailScreen(detail.name){navController.navigateUp()}
        }
    }
}