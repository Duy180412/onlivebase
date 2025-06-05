package vn.vplay.vlive.myapplication.presentation.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import vn.vplay.vlive.myapplication.R
import vn.vplay.vlive.myapplication.domain.entity.HomeContentUi
import vn.vplay.vlive.myapplication.presentation.components.ImageSlider
import vn.vplay.vlive.myapplication.presentation.extension.cast
import vn.vplay.vlive.myapplication.presentation.model.IContentUi
import vn.vplay.vlive.myapplication.presentation.model.ISlideUi

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    vm:HomeViewModel = hiltViewModel()
) {
    val slidesUi: List<IContentUi> by vm.slides.observeAsState(emptyList())
    LazyColumn {
        item {
            CircularPager(slidesUi)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CircularPager(
    items: List<IContentUi>,
    repeatCount: Int = 3,
    modifier: Modifier = Modifier
) {
    val realCount = items.size
    if (realCount == 0) return

    val extendedItems = List(realCount * repeatCount) { index ->
        items[index % realCount]
    }

    val initialPage = realCount * repeatCount / 2

    val pagerState = rememberPagerState(initialPage = initialPage) {
        extendedItems.size
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            when {
                page < realCount -> {
                    val newPage = page + realCount * (repeatCount - 1)
                    pagerState.scrollToPage(newPage)
                }
                page >= realCount * (repeatCount - 1) -> {
                    val newPage = page - realCount * (repeatCount - 1)
                    pagerState.scrollToPage(newPage)
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth().height(200.dp),
        pageSpacing = 16.dp
    ) { page ->
    }
}

@Preview(showBackground = true)
@Composable
fun CardSlide(
    iSlide: IContentUi? = null
){
    val item = iSlide.cast<ISlideUi>()
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .aspectRatio(9f / 16f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ImageSlider(
               item?.iSlideEntity?.imageUrl
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            startY = 300f
                        )
                    )
            )

            // Text content ở dưới
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = "WBG vs BLG Highlights",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "01:59:35  •  VCS 2024",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }

            // Play button góc phải dưới
            IconButton(
                onClick = { /* play */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .background(Color.White.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
            }
        }
    }
}
