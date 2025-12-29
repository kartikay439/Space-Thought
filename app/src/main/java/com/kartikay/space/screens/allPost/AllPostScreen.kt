package com.kartikay.space.screens.allPost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.kartikay.space.screens.allPost.component.PostItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllPostScreen(
    viewModel: AllPostScreenViewModel = koinViewModel()
) {
    val posts = viewModel.feed.collectAsLazyPagingItems()

    Box(Modifier.fillMaxSize().background(Color.White)) {

        LazyColumn {

            items(
                count = posts.itemCount,
                key = { posts[it]?.id ?: it }
            ) { index ->
                posts[index]?.let { PostItem(it) }
            }

            if (posts.loadState.append is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
        }

        if (posts.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
