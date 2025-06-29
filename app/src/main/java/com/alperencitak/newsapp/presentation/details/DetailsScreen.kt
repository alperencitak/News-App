package com.alperencitak.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alperencitak.newsapp.R
import com.alperencitak.newsapp.domain.model.Article
import com.alperencitak.newsapp.domain.model.Source
import com.alperencitak.newsapp.presentation.Dimens.ArticleImageHeight
import com.alperencitak.newsapp.presentation.Dimens.MediumPadding1
import com.alperencitak.newsapp.presentation.Dimens.MediumPadding3
import com.alperencitak.newsapp.presentation.details.components.DetailsTopBar
import com.alperencitak.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.SaveArticle)
            },
            onBackClick = navigateUp,
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
        ) {
            item {

                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding3))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(
                        R.color.text_title
                    )
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        R.color.body
                    )
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    NewsAppTheme {
        DetailsScreen(
            article = Article(
                author = "Joel Khalili",
                content = "Mining firms are also facing heightened competition for limited energy resources in the US, mostly from AI companies flush with venture funding. New projections from the US Department of Energy indic… [+3401 chars]",
                description = "Donald Trump pledged to cement the US as the bitcoin mining capital of the planet. The president’s sweeping tariffs stand to simultaneously undermine and advance that ambition in one swoop.",
                publishedAt = "2025-06-20T09:30:00Z",
                source = Source(
                    id = "wired",
                    name = "Wired"
                ),
                title = "A False Start on the Road to an All-American Bitcoin",
                url = "https://www.wired.com/story/a-false-start-on-the-road-to-an-all-american-bitcoin/",
                urlToImage = "https://media.wired.com/photos/68531ba03ca23a58119ac365/191:100/w_1280,c_limit/061825-amercian-bitcoin-false-start.jpg"
            ),
            event = {},
            navigateUp = {}
        )
    }
}
