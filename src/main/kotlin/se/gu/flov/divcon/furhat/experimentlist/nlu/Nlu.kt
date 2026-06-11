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

class RunExperiment(var session: Int? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> = listOf(
        "experiment @session",
        "",
        "i want to run experiment @session",
        "session @session",
    )
}
