package com.kartikay.space.screens.allPost.component

import com.kartikay.business.model.post.Post
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage

@Composable
fun PostItem(
    post: Post
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        // 👤 Username
        Text(
            text = post.username,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🖼️ Media (only if exists)
        if (post.thoughtMedia.isNotEmpty()) {
            AsyncImage(
                model = post.thoughtMedia.first(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        // 💭 Thought text
        if (post.thought.isNotBlank()) {
            Text(
                text = post.thought,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black

            )
        }
    }
}
