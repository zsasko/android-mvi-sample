package com.zsasko.android.mvi.sample.ui.screens.comments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.zsasko.android.mvi.sample.R
import com.zsasko.android.mvi.sample.data.intents.CommentIntent
import com.zsasko.android.mvi.sample.data.response.CommentsResponse
import com.zsasko.android.mvi.sample.data.state.CommentsUiState
import com.zsasko.android.mvi.sample.ui.screens.common.ErrorScreen
import com.zsasko.android.mvi.sample.ui.screens.common.LoadingScreen
import com.zsasko.android.mvi.sample.ui.theme.AndroidMVISampleTheme
import com.zsasko.android.mvi.sample.viewmodel.CommentsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    viewModel: CommentsViewModel = hiltViewModel()
) {
    val state by viewModel.commentsUiState.collectAsState()
    CommentsScreenLayout(state, {
        viewModel.handleIntent(CommentIntent.ReloadData())
    })
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommentsScreenLayout(state: CommentsUiState, onReloadDataInvoked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (state) {

                is CommentsUiState.Loaded -> {
                    CommentsListScreen(data = (state as CommentsUiState.Loaded).data)
                }

                is CommentsUiState.Error -> {
                    ErrorScreen(
                        errorText = (state as CommentsUiState.Error).errorMessage ?: "",
                        onReloadDataButtonClicked = {
                            onReloadDataInvoked()
                        })
                }

                is CommentsUiState.Loading -> {
                    LoadingScreen()
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CommentsScreenWithDataPreview() {
    val data = listOf<CommentsResponse>(
        CommentsResponse(
            postId = 1,
            id = 1,
            name = "Alice Johnson",
            email = "alice.johnson@example.com",
            body = "This post is really insightful and helped me understand the topic better."
        ),
        CommentsResponse(
            postId = 2,
            id = 2,
            name = "Bob Smith",
            email = "bob.smith@example.com",
            body = "I have a question about the second paragraph. Could you clarify it?"
        )
    )
    AndroidMVISampleTheme {
        CommentsScreenLayout(state = CommentsUiState.Loaded(data), onReloadDataInvoked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CommentsScreenWithErrorPreview() {
    AndroidMVISampleTheme {
        CommentsScreenLayout(
            state = CommentsUiState.Error("Some Error Text"),
            onReloadDataInvoked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CommentsScreenWithProgressPreview() {
    AndroidMVISampleTheme {
        CommentsScreenLayout(state = CommentsUiState.Loading(), onReloadDataInvoked = {})
    }
}