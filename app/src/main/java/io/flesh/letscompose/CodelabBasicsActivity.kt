package io.flesh.letscompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.flesh.letscompose.sampledata.SampleData
import io.flesh.letscompose.ui.theme.LetsComposeTheme

class CodelabBasicsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsComposeTheme {
                App(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isExpanded: Boolean by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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
                Text(text = "Hello,")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (isExpanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
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

@Composable
fun Greetings(
    names: List<String> = SampleData.largeListOfGreetings
) {
    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        //Just list in a recyclerView the LazyColumn Resets and Redraws the Item once it is off screen.
        //We Would need to lift the state of the selected items up to this level or better yet held in
        //a ViewModel if we want the state to be kept and rerendered when the list item is scolled
        //to and from on and off the screen.
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(R.string.greetings))
                    }
                }
                items(names) { name ->
                    Greeting(name = name)
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    // Boolean to Show the onboarding.
    // use rememberSaveable to survive configuration changes. ( I dont know if I like this yet
    // considering i think a lot of this should be saved in a ViewModel out side of the View layer
    // but again this is a good to know )

    // rememberSaveable is kinda crazy it survives configChanges as well as when scrolled off of
    // the screen with in a list. ( Dont know if this is amazing or fucking terrible ) is this a
    // something that should be saved in a seperate place can it be easily gotten? Who the
    // fuck knows. Lets Play with it move.

    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }
    Surface(modifier = modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(modifier = modifier, onContinueClicked = {
                // flip shouldShowOnboarding so that the the composable is re-evaluated and it is
                // decided to show the onboarding or greetings.
                shouldShowOnboarding = false
            })
        } else {
            Greetings()
        }
    }
}


@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center, // ARRANGEMENT
            horizontalAlignment = Alignment.CenterHorizontally // ALIGNMENT ( Figure our the difference. ) just so you dont get confused.
        ) {
            Text(text = "Welcome to Lets Compose!")
            Button(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = { onContinueClicked.invoke() }) {
                Text(text = "Continue")
            }
        }
    }

}

//region PREVIEWS
// These should really be in there own files and shit this is a lot for one file.
@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    LetsComposeTheme {
        OnboardingScreen {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingsPreview() {
    LetsComposeTheme {
        Greetings()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AppPreview() {
    LetsComposeTheme {
        App(modifier = Modifier.fillMaxSize())
    }
}
//endregion