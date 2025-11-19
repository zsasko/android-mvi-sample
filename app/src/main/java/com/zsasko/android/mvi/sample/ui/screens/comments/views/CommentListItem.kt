package com.zsasko.android.mvi.sample.ui.screens.comments.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zsasko.android.mvi.sample.data.response.CommentsResponse

@Composable
fun CommentListItem(modifier: Modifier = Modifier, item: CommentsResponse) {
    Column(modifier = modifier) {
        Text(
            text = item.name,
            modifier = Modifier.padding(bottom = 5.dp),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item.body,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
fun PreviewCommentListItem() {
    CommentListItem(
        item = CommentsResponse(
            postId = 1,
            id = 1,
            name = "Id labore ex et quam laborum",
            email = "Eliseo@gardner.biz",
            body = "Laudantium enim quasi est quidem magnam voluptate ipsam eos."
        )
    )
}