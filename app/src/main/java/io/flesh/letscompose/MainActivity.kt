package io.flesh.letscompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import io.flesh.letscompose.utils.previewutils.WindowWidthSizeClassPreview
import io.flesh.letscompose.utils.previewutils.WindowWidthSizePreviewParameterProvider
import io.flesh.letscompose.sampledata.SampleData
import io.flesh.letscompose.ui.theme.LetsComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsComposeApp(windowSize = calculateWindowSizeClass(activity = this).widthSizeClass)
        }
    }
}

@Composable
fun LetsComposeApp(windowSize: WindowWidthSizeClass) {
    LetsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LetsComposePLaceList()
        }
    }
}


@Composable
fun LetsComposePLaceList(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LazyColumn {
        items(SampleData.letComposeList) {
            LetsCompostPlaceItem(name = it.title, expandedText = stringResource(id = R.string.author, it.author)) {
                val destination = Intent(context, it.destination)
                startActivity(context, destination, Bundle())
            }
        }
    }
}

@Composable
fun LetsCompostPlaceItem(
    name: String,
    expandedText: String = "Composem ipsum color sit lazy, padding theme elit, sed do bouncy. "
        .repeat(4),
    destination: () -> Unit = {}
) {
    var isExpanded: Boolean by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { destination.invoke() }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (isExpanded) {
                    Text(
                        text = expandedText,
                    )
                }
            }
            IconButton(
                onClick = {
                    isExpanded = !isExpanded
                }) {
                Icon(
                    imageVector = if (isExpanded) {
                        Icons.Filled.ExpandLess
                    } else {
                        Icons.Filled.ExpandMore
                    },
                    contentDescription = if (isExpanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(
    @PreviewParameter(WindowWidthSizePreviewParameterProvider::class) windowWidth: WindowWidthSizeClassPreview,
) {
    LetsComposeTheme {
        LetsComposeApp(
            windowSize = windowWidth.value
        )
    }
}