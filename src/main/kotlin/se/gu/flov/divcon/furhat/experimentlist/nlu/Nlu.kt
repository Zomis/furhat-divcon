package se.gu.flov.divcon.furhat.experimentlist.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.util.Language

class SessionId : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
        )
    }
}

class RunExperiment(var session: Number? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> = listOf(
        "@session",
        "experiment @session",
        "experiment 3",
        "session 5",
        "i want to run experiment @session",
        "i am running experiment @session",
        "i want to run session @session",
        "i am running session @session",
        "session @session",
    )
}
