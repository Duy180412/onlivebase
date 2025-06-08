package vn.vplay.vlive.myapplication.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage


@Preview(showBackground = true)
@Composable
fun PreviewImage() {
//    ImageSlider()
}

@Composable
fun ImageByUrl(imageUrl: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}