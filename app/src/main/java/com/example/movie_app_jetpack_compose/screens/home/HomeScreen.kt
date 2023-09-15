package com.example.movie_app_jetpack_compose.screens.home

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movie_app_jetpack_compose.R
import com.example.movie_app_jetpack_compose.model.MovieModel
import com.example.movie_app_jetpack_compose.model.getMovies
import com.example.movie_app_jetpack_compose.navigation.MovieScreens
import com.example.movie_app_jetpack_compose.widgets.MovieRow
import okhttp3.internal.wait


@Composable
fun HomeScreen(navController: NavController){
    Scaffold(modifier = Modifier.background(color = Color.White), topBar = {
        TopAppBar(
            backgroundColor = Color.White, elevation = 0.dp,
            title = {
                Text(
                    text = "Movies",
                    color = colorResource(R.color.newColor), // Customize the title text color
                    modifier = Modifier.padding(8.dp)
                )
            },
        )
    },
        content = {
            Column(modifier = Modifier
                .padding(it)
                .background(color = Color.White)) {
                MainContent(navController=navController)
            }
        })

}

@Composable
fun MainContent(navController: NavController, movielist : List<MovieModel> = getMovies()){

//    val items = (1..10).toList()
//
//    LazyColumn {
//        itemsIndexed(items) { index, item ->
//            val transition = rememberInfiniteTransition()
//
//            val offsetX by transition.animateFloat(
//                initialValue = 300f, // Start position (off the screen to the right)
//                targetValue = 0f,
//                // End position (on the screen)
//                animationSpec = infiniteRepeatable(
//                    animation = tween(
//                        durationMillis = 1000,
//                        delayMillis = 100 * index
//                    ),
//                    repeatMode = RepeatMode.Restart,
//
//                )
//            )
//
//
//
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .offset(x = offsetX.dp)
//            ) {
//                Text(text = "Item $item", style = MaterialTheme.typography.h6)
//            }
//        }
//    }



    Column(modifier = Modifier
        .padding(16.dp)
        .background(color = Color.White)) {
        LazyColumn{
            items(items = movielist){

                MovieRow(movie_name = it){movie ->
                    navController.navigate(route = MovieScreens.DetailScreen.name+"/$movie")
                   // Log.d("TAG", "MainContent: $movie")
                }
            }
        }

    }
}
