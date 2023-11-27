package io.flesh.letscompose.sampledata

import io.flesh.letscompose.R
import io.flesh.letscompose.models.Answer
import io.flesh.letscompose.models.Message

object SampleData {

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

}