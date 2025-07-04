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
import com.alperencitak.newsapp.presentation.news_navigator.NewsNavigator
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
                NewsNavigator()
            }
        }
    }

}