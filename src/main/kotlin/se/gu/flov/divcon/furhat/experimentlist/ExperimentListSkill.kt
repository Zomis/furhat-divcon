package se.gu.flov.divcon.furhat.experimentlist

import se.gu.flov.divcon.furhat.experimentlist.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class ExperimentListSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
