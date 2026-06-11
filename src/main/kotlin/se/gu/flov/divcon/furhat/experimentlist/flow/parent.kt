package se.gu.flov.divcon.furhat.experimentlist.flow

import furhatos.flow.kotlin.*
import se.gu.flov.divcon.furhat.experimentlist.flow.main.Idle

val Parent: State = state {

    onUserLeave(instant = true) {
        when {
            users.count == 0 -> goto(Idle)
            it == users.current -> furhat.attend(users.other)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}