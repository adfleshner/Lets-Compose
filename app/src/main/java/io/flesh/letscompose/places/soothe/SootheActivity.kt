package io.flesh.letscompose.places.soothe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.flesh.letscompose.R
import io.flesh.letscompose.models.AlignItem
import io.flesh.letscompose.sampledata.SampleData
import io.flesh.letscompose.ui.theme.LetsComposeTheme

class SootheActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheApp(windowSize = calculateWindowSizeClass(activity = this))
        }
    }
}

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    LetsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            when (windowSize.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    MySoothePortrait()
                }

                WindowWidthSizeClass.Medium,
                WindowWidthSizeClass.Expanded -> {
                    MySootheAppLandscape()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySoothePortrait() {
    LetsComposeTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun MySootheAppLandscape() {
    LetsComposeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PortraitPreview() {
    MySoothePortrait()
}


@Preview(showBackground = true)
@Composable
fun LandscapPreview() {
    MySootheAppLandscape()
}


@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_settings)
                )
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_settings))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.itsme) {
            AlignYourBodyRow(
                alignYouBodyData = SampleData.favorites.subList(0, 3)
            )
        }
        HomeSection(title = R.string.itsme) {
            FavoritesGrid(
                favorites = SampleData.favorites
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun HomeScreenPreview() {
    LetsComposeTheme {
        HomeScreen()
    }
}


@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content() // This is a cool way to add this.
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    LetsComposeTheme {
        HomeSection(title = R.string.itsme) {
            AlignYourBodyRow(
                alignYouBodyData = SampleData.favorites
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    Column {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                Text(stringResource(R.string.placeholder_search))
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier

    ) {
        Image(
            painter = painterResource(id = drawable),
            contentScale = ContentScale.Crop,
            contentDescription = "me",
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int, @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium, modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "its me again",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyRow(alignYouBodyData: List<AlignItem>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        // adds a space at the front and end of the row.
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        // adds a space between each item.
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(alignYouBodyData) { item ->
            AlignYourBodyElement(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun FavoritesGrid(favorites: List<AlignItem>, modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier.height(168.dp)
    ) {
        items(favorites) { item ->
            FavoriteCollectionCard(drawable = item.drawable, text = item.text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesGridPreview() {
    FavoritesGrid(
        favorites = SampleData.favorites
    )
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    AlignYourBodyRow(
        SampleData.favorites
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        drawable = R.drawable.itsme,
        text = R.string.itsme,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
    LetsComposeTheme {
        AlignYourBodyElement(
            drawable = R.drawable.itsme, text = R.string.itsme, modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LetsComposeTheme {
        SearchBar(Modifier.padding(horizontal = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SootheBottomNavigationPreview() {
    LetsComposeTheme {
        SootheBottomNavigation()
    }
}