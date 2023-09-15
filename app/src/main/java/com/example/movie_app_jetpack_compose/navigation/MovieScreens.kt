package com.example.movie_app_jetpack_compose.navigation

enum class MovieScreens{
    HomeScreen,
    DetailScreen;
    companion object {
        fun fromRoute(route: String?) : MovieScreens =when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}