package vn.vplay.vlive.myapplication.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import retrofit2.http.Url


@Composable
fun PreviewImage() {
//    ImageSlider()
}

@Composable
fun ImageSlider(imageUrl: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}