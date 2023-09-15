package com.example.movie_app_jetpack_compose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.movie_app_jetpack_compose.model.MovieModel
import com.example.movie_app_jetpack_compose.model.getMovies

fun Modifier.bounceClick(
    animationDuration: Int = 10,
    scaleDown: Float = 0.9f,
    onClick: () -> Unit
) = composed {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val animatable = remember {
        Animatable(1f)
    }

    LaunchedEffect(key1 = isPressed) {
        if (isPressed) {
            animatable.animateTo(scaleDown)
        } else animatable.animateTo(1f)
    }

    Modifier
        .graphicsLayer {
            val scale = animatable.value
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
}

@Preview
@Composable
fun MovieRow(movie_name: MovieModel = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    val colors = listOf(
        Color.Transparent,
        Color(android.graphics.Color.parseColor("#0C0404")),
    )
    val isexpanded = remember {
        mutableStateOf(false)
    }
//
    Card(
        modifier = Modifier
            .padding(4.dp)
            .bounceClick {
                onItemClick(movie_name.id)
            }
            .fillMaxWidth()
//        .clickable {
//            onItemClick(movie_name.id)
//        }
            .height(170.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp,

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // First composable (at the bottom of the stack)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(230.dp)
                    .background(Color.Transparent)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie_name.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie image",

                    contentScale = ContentScale.Crop
                )
            }

            // Second composable (stacked on top)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 70.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors,
                            startX = 0f,
                            endX = 400f// Adjust the end point as needed
                        )
                    ),

                ) {
                Column(
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .padding(start = 40.dp)
                ) {

                    Text(
                        text = movie_name.title,
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                    Text(
                        text = "Director: ${movie_name.director}",
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )
                    Text(
                        text = "Released on: ${movie_name.year}",
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )
                    LazyColumn() {
                        item {
                            AnimatedVisibility(visible = isexpanded.value) {
                                Column() {
                                    Text(buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.White,
                                                fontSize = 13.sp
                                            )
                                        ) {
                                            append("Plot: ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.White,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Light
                                            )
                                        ) {
                                            append(movie_name.plot)
                                        }


                                    })
                                    Divider(color = Color.White)
                                    Text(
                                        text = "Actors: ${movie_name.actors}",
                                        style = MaterialTheme.typography.caption,
                                        color = Color.White, fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Rating: ${movie_name.rating}",
                                        style = MaterialTheme.typography.caption,
                                        color = Color.White, fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        item {
                            Icon(
                                imageVector = if (!isexpanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                                contentDescription = "show more button",
                                modifier = Modifier

                                    .size(25.dp)
                                    .bounceClick {
                                        isexpanded.value = !isexpanded.value
                                    }, tint = Color.White
                            )
                        }
                    }


                }
            }

            // Third composable (stacked on top)
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(32.dp) // Add more padding
//                .background(Color.Blue)
//        )

            // You can add more composables as needed
        }

    }
}
