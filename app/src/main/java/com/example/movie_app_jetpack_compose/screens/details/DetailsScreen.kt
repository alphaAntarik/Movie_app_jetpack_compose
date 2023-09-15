package com.example.movie_app_jetpack_compose.screens.details


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie_app_jetpack_compose.model.MovieModel
import com.example.movie_app_jetpack_compose.model.getMovies

fun getMovieDetailsByid(uid: String): MovieModel? {

    return getMovies().find { it.id == uid }
}


@Preview
@Composable
fun DetailsScreen(
    navController: NavController = rememberNavController(),
    movie_name: String? = getMovies()[0].id
) {


    var moviedetails = getMovieDetailsByid(movie_name!!)

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = moviedetails!!.title,
                onBackClick = { navController.popBackStack() })
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(moviedetails!!.images[0])
                            .crossfade(true)
                            .build(),
                        modifier = Modifier.height(300.dp),
                        contentDescription = "Movie image",

                        contentScale = ContentScale.Crop
                    )
                }
                item {

                    Text(text = moviedetails!!.title, fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                }
                item {

                    Text(text = moviedetails!!.rating, fontSize = 20.sp,modifier = Modifier.padding(10.dp))
                }
                item {

                    Text(text = moviedetails!!.actors, fontSize = 20.sp,modifier = Modifier.padding(10.dp))
                }
                item {

                    Text(text = moviedetails!!.genre, fontSize = 20.sp,modifier = Modifier.padding(10.dp))
                }
                item {

                    Text(text = moviedetails!!.plot, fontSize = 20.sp,modifier = Modifier.padding(10.dp))
                }
                item {

                    Text(text = moviedetails!!.director, fontSize = 20.sp,modifier = Modifier.padding(10.dp))
                }
                item {

                    Text(text = moviedetails!!.year, fontSize = 20.sp,modifier = Modifier.padding(10.dp))

                }
                item {

                    Text(text = moviedetails!!.poster, fontSize = 20.sp,modifier = Modifier.padding(10.dp))
                }


            }
        }
    )

}

@Composable
fun MyTopAppBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentColor = Color.Black, // Customize the content color (text color)
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                text = title,
                color = Color.Black, // Customize the title text color
                modifier = Modifier.padding(8.dp)
            )
        },
        actions = {
            // You can add additional actions here if needed
        }
    )
}


