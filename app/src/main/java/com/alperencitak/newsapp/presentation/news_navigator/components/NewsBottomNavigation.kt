package com.alperencitak.newsapp.presentation.news_navigator.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alperencitak.newsapp.R
import com.alperencitak.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.alperencitak.newsapp.presentation.Dimens.IconSize
import com.alperencitak.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(R.color.body),
                    unselectedTextColor = colorResource(R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }

    }

}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview(){
    NewsAppTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(
                    icon = R.drawable.home,
                    text = "Home"
                ),
                BottomNavigationItem(
                    icon = R.drawable.search,
                    text = "Search"
                ),
                BottomNavigationItem(
                    icon = R.drawable.selected_bookmark,
                    text = "Bookmark"
                )
            ),
            selected = 0,
            onItemClick = {}
        )
    }
}

