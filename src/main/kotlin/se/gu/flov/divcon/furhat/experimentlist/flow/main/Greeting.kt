package se.gu.flov.divcon.furhat.experimentlist.flow.main

import furhatos.flow.kotlin.Furhat
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import se.gu.flov.divcon.furhat.experimentlist.flow.Parent
import se.gu.flov.divcon.furhat.experimentlist.nlu.RunExperiment
import se.gu.flov.divcon.furhat.experimentlist.nlu.SessionId

enum class Task {
    Joke, Balloon, Mingle,
}

enum class Condition {
    Live, Text, Video,
}

val sessions = listOf(
    listOf(Condition.Text to Task.Joke, Condition.Video to Task.Balloon, Condition.Live to Task.Mingle),
    listOf(Condition.Text to Task.Mingle, Condition.Video to Task.Joke, Condition.Live to Task.Balloon),
    listOf(Condition.Live to Task.Mingle, Condition.Text to Task.Joke, Condition.Video to Task.Balloon),
    listOf(Condition.Text to Task.Balloon, Condition.Video to Task.Mingle, Condition.Live to Task.Joke),
    listOf(Condition.Video to Task.Balloon, Condition.Live to Task.Mingle, Condition.Text to Task.Joke),
    listOf(Condition.Live to Task.Balloon, Condition.Text to Task.Mingle, Condition.Video to Task.Joke),
    listOf(Condition.Live to Task.Joke, Condition.Text to Task.Balloon, Condition.Video to Task.Mingle),
    listOf(Condition.Video to Task.Mingle, Condition.Live to Task.Joke, Condition.Text to Task.Balloon),
    listOf(Condition.Video to Task.Joke, Condition.Live to Task.Balloon, Condition.Text to Task.Mingle),
)


fun response(furhat: Furhat, it: RunExperiment?) {
    if (it == null) {
        furhat.say("No intent found.")
        return
    }
    val sessionValue = it.session
//        val sessionNumber = it.intent.session?.values?.indexOf(sessionValue)
    val sessionNumber = sessionValue
    if (sessionNumber == null) {
        furhat.say("No session specified.")
        return
    }

    val session = sessions.getOrNull(sessionNumber - 1)
    if (session == null) {
        furhat.say("Session $sessionNumber out of range")
        return
    }

    do {
        furhat.say("Session $sessionNumber has the following")
        val taskNumber = listOf("first", "second", "third")
        for (i in 0 until 3) {
            val combination = session[i]
            furhat.say("${taskNumber[i]} task: ${combination.first} ${combination.second}")
        }
    } while (furhat.askYN("Do you want me to repeat?"))
}

val Greeting : State = state(Parent) {
    onEntry {
        // furhat.say("")
        val s = furhat.askFor<RunExperiment>("So you are running an experiment, which session are you running today?", stateDefinition = null)
        response(furhat, s)
//        furhat.listen()
    }

//    onResponse<RunExperiment> {
//        response(it.intent)
//    }
}
