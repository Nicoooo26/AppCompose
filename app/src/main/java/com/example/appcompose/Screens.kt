package com.example.appcompose

import kotlinx.serialization.Serializable
@Serializable
object Login

@Serializable
object Searcher

@Serializable
data class Profile(val name:String)

@Serializable
data class Detail(val name:String)