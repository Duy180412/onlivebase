package vn.vplay.vlive.myapplication.presentation.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import vn.vplay.vlive.myapplication.R
import vn.vplay.vlive.myapplication.data.enumext.StatusEvent
import vn.vplay.vlive.myapplication.domain.entity.ILiveSlideEntity
import vn.vplay.vlive.myapplication.domain.entity.IVodSlideEntity
import vn.vplay.vlive.myapplication.domain.entity.IWebSlideEntity
import vn.vplay.vlive.myapplication.domain.help.collectData
import vn.vplay.vlive.myapplication.presentation.components.ImageByUrl
import vn.vplay.vlive.myapplication.presentation.components.TopBar
import vn.vplay.vlive.myapplication.presentation.extension.cast
import vn.vplay.vlive.myapplication.presentation.help.toTimeDayDdMm
import vn.vplay.vlive.myapplication.presentation.model.IContentUi
import vn.vplay.vlive.myapplication.presentation.model.ISlideItemUi
import vn.vplay.vlive.myapplication.presentation.model.ISlideUi
import vn.vplay.vlive.myapplication.presentation.theme.Color131313_50
import vn.vplay.vlive.myapplication.presentation.theme.ColorFaFaFa
import vn.vplay.vlive.myapplication.presentation.theme.textSize16Semi
import kotlin.math.abs

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel()
) {
    val slide by vm.slides.collectData()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(104.dp)
                    .align(Alignment.TopCenter)
                    .zIndex(1f)
                    .background(Color131313_50)
            )

            LazyColumn(
                modifier = Modifier
            ) {
                item {
                    CircularPager(
                        items = slide,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }


    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CircularPager(
    items: IContentUi?,
    repeatCount: Int = 3,
    modifier: Modifier = Modifier
) {
    val list = items.cast<ISlideUi>()?.list.orEmpty()
    val realCount = list.size
    if (realCount == 0) return

    val extendedItems = List(realCount * repeatCount) { index ->
        list[index % realCount]
    }

    val initialPage = realCount

    val pagerState = rememberPagerState(initialPage = initialPage) {
        extendedItems.size
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                if (!pagerState.isScrollInProgress) {
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
    }

    val currentItem = extendedItems[pagerState.currentPage]
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = 336.dp
    val sidePadding = (screenWidth - cardWidth) / 2
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        AsyncImage(
            model = currentItem.iSlideEntity.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(top = 120.dp),
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = sidePadding),

            ) { page ->
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val absOffset = abs(pageOffset).coerceIn(0f, 1f)
            val heightDp = lerp(504.dp, 433.dp, absOffset)

            CardSlide(
                extendedItems[page],
                modifier = Modifier
                    .width(cardWidth)
                    .height(heightDp)
            )
        }
    }
}

@Composable
fun CardSlide(
    iSlide: ISlideItemUi? = null,
    modifier: Modifier
) {
    val item = iSlide?.iSlideEntity

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        val subTitle = when (item) {
            is IWebSlideEntity -> {
                stringResource(R.string.txt_news_is_updating)
            }

            is IVodSlideEntity -> {
                stringResource(R.string.text_sub_title_slide_vod, item.duration, item.leagueName)
            }

            is ILiveSlideEntity -> {
                when (item.statusEvent) {
                    StatusEvent.NotStart -> {
                        item.startTime.toTimeDayDdMm()
                    }

                    StatusEvent.Live, StatusEvent.Finnish -> {
                        stringResource(R.string.live_and_league, item.leagueName)
                    }

                    else -> ""
                }
            }
            else -> ""
        }

        Box(modifier = Modifier.fillMaxSize()) {
            ImageByUrl(
                iSlide?.iSlideEntity?.imageUrl
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = iSlide?.iSlideEntity?.name.orEmpty(),
                    style = textSize16Semi,
                    color = ColorFaFaFa,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = subTitle ?: "",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }

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
