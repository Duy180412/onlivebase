package vn.vplay.vlive.myapplication.presentation.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import vn.vplay.vlive.myapplication.domain.entity.HomeContentUi
import vn.vplay.vlive.myapplication.presentation.model.IContentUi

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
                    // Nếu gần đầu, nhảy về giữa
                    val newPage = page + realCount * (repeatCount - 1)
                    pagerState.scrollToPage(newPage)
                }
                page >= realCount * (repeatCount - 1) -> {
                    // Nếu gần cuối, nhảy về giữa
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
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = extendedItems[page].toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
