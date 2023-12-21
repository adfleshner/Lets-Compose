package io.flesh.letscompose.sampledata

import io.flesh.letscompose.places.Conversation.ConversationActivity
import io.flesh.letscompose.places.codelabbasics.CodelabBasicsActivity
import io.flesh.letscompose.models.LetsComposePlaces
import io.flesh.letscompose.R
import io.flesh.letscompose.models.AlignItem
import io.flesh.letscompose.models.Answer
import io.flesh.letscompose.models.Message
import io.flesh.letscompose.models.WellnessTask
import io.flesh.letscompose.places.soothe.SootheActivity
import io.flesh.letscompose.places.states.StatesActivity
import io.flesh.letscompose.places.survey.SurveyActivity

object SampleData {

    val favorites: List<AlignItem> = listOf(
        AlignItem(R.drawable.itsme, R.string.itsme),
        AlignItem(R.drawable.itsme, R.string.itsme),
        AlignItem(R.drawable.itsme, R.string.itsme),
        AlignItem(R.drawable.itsme, R.string.itsme),
        AlignItem(R.drawable.itsme, R.string.itsme),
        AlignItem(R.drawable.itsme, R.string.itsme)
    )


    val smallListOfGreetings: List<String> get() = listOf("World","Compose","Android")
    val largeListOfGreetings: List<String> get() = List(1_000) { "Android $it" }
    val messageSample: Message = conversionSample[0]

    val conversionSample get() = listOf(
        Message("Leo", "Hey, take a look at Jetpack Compose, it's great!"),
        Message("Leo", "Test Test Test"),
        Message("Leo", "Look and some android version : \nCupcake,\nDonut,\nEclair,\nFroyo "),
        Message("Leo", "Yo, Kotlin is kinda cool"),
        Message("Leo", "Here is a much longer sentence that I can use to look at really long text. Do you think that is long enough?? Probably not right well hey I'm just a dog and I think that this is long enough for now and Ill will add more if I thnk that I need to. \nOk thanks bye"),
        Message("Leo", "Ok Jetpack Compose is pretty easy so far."),
        Message("Leo", "But what is it going to look like when I need to build an app that is bigger then just one screen?"),
    )

    val answerSample : Answer get() = answersSample[0]

    val answersSample get() = listOf(
        Answer(image = R.drawable.profile_picture, text =  "Question 1"),
        Answer(image = R.drawable.potato_picture,text =  "Question 2"),
        Answer(image = R.drawable.profile_picture,text =  "Question 3"),
        Answer(image = R.drawable.potato_picture,text =  "Question 4")
    )


    val letComposeList get() = listOf(
        LetsComposePlaces("Conversation", destination = ConversationActivity::class.java),
        LetsComposePlaces("CodeLabsBasics", destination = CodelabBasicsActivity::class.java),
        LetsComposePlaces("Survey", destination = SurveyActivity::class.java),
        LetsComposePlaces("Soothe", destination = SootheActivity::class.java),
        LetsComposePlaces("States", destination = StatesActivity::class.java),

    )

    val wellnessTasks get() = mutableListOf<WellnessTask>(
        WellnessTask(1 , "Did you compose today?"),
        WellnessTask(2 , "Did you practice spanish today?")
    ).also {list ->
        repeat(60){
            val newId = it+3
            list.add(WellnessTask(newId, "Task # $newId"))
        }
    }

}