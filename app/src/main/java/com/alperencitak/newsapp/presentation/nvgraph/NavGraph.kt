package com.alperencitak.newsapp.presentation.nvgraph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.newsapp.domain.model.Article
import com.alperencitak.newsapp.domain.model.Source
import com.alperencitak.newsapp.presentation.bookmark.BookmarkScreen
import com.alperencitak.newsapp.presentation.bookmark.BookmarkViewModel
import com.alperencitak.newsapp.presentation.details.DetailsScreen
import com.alperencitak.newsapp.presentation.home.HomeScreen
import com.alperencitak.newsapp.presentation.home.HomeViewModel
import com.alperencitak.newsapp.presentation.onboarding.OnBoardingScreen
import com.alperencitak.newsapp.presentation.onboarding.OnBoardingViewModel
import com.alperencitak.newsapp.presentation.search.SearchScreen
import com.alperencitak.newsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                    /*
                    event = viewModel::onEvent

                    =

                    event = {
                        viewModel.onEvent(it)
                    }
                     */
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigate = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable(
                route = Route.SearchScreen.route
            ) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    event = viewModel::onEvent,
                    state = viewModel.state.value,
                    navigate = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable(
                route = Route.BookmarkScreen.route
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(
                    state = viewModel.state.value,
                    navigate = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable(
                route = Route.DetailsScreen.route
            ) {
                // CHANGE THİS
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
    }

}