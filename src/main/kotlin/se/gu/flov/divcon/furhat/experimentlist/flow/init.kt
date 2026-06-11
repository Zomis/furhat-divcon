package se.gu.flov.divcon.furhat.experimentlist.flow

import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.furhat.characters.Characters
import furhatos.flow.kotlin.voice.Voice
import se.gu.flov.divcon.furhat.experimentlist.flow.main.Idle

val Init : State = state {
    init {
        furhat.setCharacter(Characters.Adult.Rania)
        // Set default interaction parameters
        users.setSimpleEngagementPolicy(1.0, 2)

        furhat.voice = Voice("Matthew")
        goto(Idle)
    }
}
