package com.alperencitak.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.alperencitak.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "The Latest News in Your Pocket!",
        description = "Follow local and global news instantly. Stay up to date with unbiased, reliable and fast news flow.",
        image = R.drawable.city
    ),
    Page(
        title = "Content by Interest",
        description = "Sports, economy, technology and more... Choose your areas of interest and don't miss special news for you.",
        image = R.drawable.sport
    ),
    Page(
        title = "Welcome to News App!",
        description = "Start exploring the app now.",
        image = R.drawable.television
    )
)
