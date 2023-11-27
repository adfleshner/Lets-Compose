package io.flesh.letscompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
// All three of these are needed for remember to fucking work........
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
//
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.flesh.letscompose.models.Message
import io.flesh.letscompose.sampledata.SampleData
import io.flesh.letscompose.ui.theme.LetsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FullScreen()
        }
    }
}

//region ================================Composables======================================//

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        // be sure to import the items from LazyListScope that can just take items otherwise you
        // will be sitting there scratching your head wondering WTF is going on.
        items(messages) { message ->
            MessageCard(message = message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    // by remember unwraps the state to the value of the state and remembers is so that it is not
    // recalculated every time it is recomposed.
    // remember is one of those gotta import the right thing.
    // I had to import it like 3 times for it to fucking work WTF!!
    //====================================================================
    // We keep track if the message is expanded or not in this variable
    var isMessageExpanded: Boolean by remember { mutableStateOf(false) }
    val surfaceColor: Color by animateColorAsState(
        targetValue = if (isMessageExpanded) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surface
        },
        label = ""
    )

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(size = 40.dp)
                .clip(shape = CircleShape)
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )
        // add a spacer here instead of trying to add padding to the end of the image or to the
        // beginning of the Column. So that no goofy shit happens.
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.clickable {
            // what to do when the column is clicked.
            // this toggles the state.
            isMessageExpanded = !isMessageExpanded
        }) {
            Text(
                text = message.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            // Same here instead of using padding of the other views just add a spacer. its cleaner
            // will help when creating other composables.
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(all = 1.dp)
            ) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isMessageExpanded) {
                        Int.MAX_VALUE // this could be a bad Idea Buuuuuuut its a tut so,
                        // I'll leave it for now
                    } else {
                        1
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

//endregion ================================Composables======================================//
//region ================================Layout Building======================================//
// Use for both Creating the app and Showing the preview? Is this a good idea?
@Composable
fun FullScreen() {
    LetsComposeTheme {
        // A surface container using the 'background' color from the theme
        // this also makes it so the preview shows the entire screen.
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Full Conversation
            Conversation(messages = SampleData.conversionSample)
            //MessageCard(Message("Leo", "Hey, take a look at Jetpack Compose, it's great!"))
        }
    }
}

//endregion ================================Layout Building======================================//
//region ================================PREVIEWS======================================//
// Good to know that you are able to have multiple previews in compose.
// Also good to know that you can have multiple Previews on one Preview Composable.

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreviewScreen() {
    FullScreen()
}

@Preview(name = "Light Message", showBackground = true)
@Preview(name = "Dark Message", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MessageCardPreview() {
    LetsComposeTheme {
        Surface {
            MessageCard(message = SampleData.messageSample)
        }
    }
}

@Preview(name = "Light Conversation", showBackground = true)
@Preview(
    name = "Dark Conversation",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ConversionPreview() {
    LetsComposeTheme {
        Conversation(messages = SampleData.conversionSample)
    }
}
//endregion ================================PREVIEWS======================================//