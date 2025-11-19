package com.zsasko.android.mvi.sample.ui.screens.comments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.zsasko.android.mvi.sample.R
import com.zsasko.android.mvi.sample.TEST_TAG_COMMENTS_SCREEN_COMMENTS_LIST
import com.zsasko.android.mvi.sample.data.response.CommentsResponse
import com.zsasko.android.mvi.sample.ui.screens.comments.views.CommentListItem
import com.zsasko.android.mvi.sample.ui.theme.AndroidMVISampleTheme

@Composable
fun CommentsListScreen(modifier: Modifier = Modifier, data: List<CommentsResponse>) {
    val itemPadding = dimensionResource(id = R.dimen.list_item_padding)
    LazyColumn(
        contentPadding = PaddingValues(horizontal = itemPadding),
        verticalArrangement = Arrangement.spacedBy(itemPadding),
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = itemPadding)
            .testTag(TEST_TAG_COMMENTS_SCREEN_COMMENTS_LIST)
    ) {
        items(data) { comment ->
            CommentListItem(item = comment)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CommentsListScreenPreview() {
    AndroidMVISampleTheme {
        CommentsListScreen(
            data = listOf(
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
                ),
                CommentsResponse(
                    postId = 3,
                    id = 3,
                    name = "Carol Williams",
                    email = "carol.williams@example.com",
                    body = "Great explanation! I especially liked the examples provided."
                )
            )
        )
    }
}