package com.example.appcompose

import kotlinx.serialization.Serializable

object NavRoutes {
    const val LOGIN = "Login"
    const val SEARCHER = "Searcher"
    // Ambos argumentos se definen en la ruta
    val CHECK_ACCESS_WITH_ARGS = "check_access/{username}/{password}"
}
@Serializable
data class Login(val msg:String)

@Serializable
object Searcher

@Serializable
data class User(val username:String, val password:String)

@Serializable
data class Profile(val name:String)

@Serializable
data class Detail(val name:String)