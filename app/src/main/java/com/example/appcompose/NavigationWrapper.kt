package com.example.appcompose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.appcompose.dataBase.DBHelper

@Composable
fun NavigationWrapper() {
    Log.i("ANTES","Se crea la BBDD")
    val context = LocalContext.current
    val db = DBHelper(context,null)
    db.addUser("admin","password")

    val navController = rememberNavController()
    NavHost( navController = navController, startDestination= NavRoutes.LOGIN){
        composable(NavRoutes.LOGIN){
            LoginScreen(
                navigateToCheckAccess = {username, password ->
                    navController.navigate("check_access/$username/$password")
                }
            )
        }

        composable(
            route = NavRoutes.CHECK_ACCESS_WITH_ARGS,
            arguments = listOf(
                navArgument("username"){type= NavType.StringType},
                navArgument("password"){type= NavType.StringType}
                )
        ) { backEntry ->
            val username: String? = backEntry.arguments?.getString("username")
            val password: String? = backEntry.arguments?.getString("password")

            LaunchedEffect(key1 = Unit) {
                val existe =  db.isThereAnyone(username,password)
                if(existe){
                    navController.navigate(NavRoutes.SEARCHER){
                        popUpTo(NavRoutes.LOGIN){inclusive = false}
                    }
                } else {
                    navController.navigateUp()
                }
            }
        }

        composable(NavRoutes.SEARCHER){
            SearcherScreen{ name -> navController.navigate(Profile(name = name)) }
        }
        composable<Profile>{ backStackEntry ->
            val profile:Profile = backStackEntry.toRoute()
            ProfileScreen(profile.name) { navController.navigate(Detail(name = profile.name))}
        }
        composable<Detail>{ backStackEntry ->
            val detail:Detail = backStackEntry.toRoute()
            DetailScreen(detail.name){navController.navigateUp()}
        }
    }
}