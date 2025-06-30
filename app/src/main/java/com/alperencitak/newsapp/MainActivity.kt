package com.alperencitak.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.alperencitak.newsapp.data.local.NewsDao
import com.alperencitak.newsapp.domain.model.Article
import com.alperencitak.newsapp.domain.model.Source
import com.alperencitak.newsapp.presentation.nvgraph.NavGraph
import com.alperencitak.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    @Inject
    lateinit var newsDao: NewsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            newsDao.upsert(
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
                )
            )
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        setContent {
            NewsAppTheme {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setNavigationBarColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}