package game_manager.quest_manager

import logListView
import player
import returnedResponse
import shouldReturnResponse
import shouldStillCommandHandle
import kotlin.test.assertTrue

abstract class Quest {
    var isActive = false
    var isComplete = false
    var currentStep = 0
    abstract fun nextStep(): Boolean
    abstract fun completeQuest()
    abstract fun getState()

}

class SetNameQuest: Quest() {
    override fun nextStep(): Boolean {
        if (isActive) {
            when (currentStep) {
                0 -> return step00()
                1 -> return step01()
                else -> return false
            }
        } else {
            return false
        }
    }

    override fun getState() {

    }

    fun step00(): Boolean {
        logListView.items.add("""
            Hey there! You look a bit lost. From a different dimension maybe?
            Haha, thought so. Listen, we're gonna getcha home, but I'm gonna
            need your name first. So how's bout it?
            """)
        shouldStillCommandHandle = true
        currentStep = 1
        return true
    }

    fun step00a(): Boolean {
        logListView.items.add("""
            Yeah a name - like characters that go together and make a sound.
            A name can have a combination of up to 24 letters/numbers
            together. Those are the rules here.
            """)
        shouldStillCommandHandle = true
        currentStep = 1
        return true
    }

    fun step01(): Boolean {
        if (returnedResponse.isNotEmpty() && returnedResponse.length < 24) {
            player.name = returnedResponse

            logListView.items.add("""
            ${player.name}, eh? Bold.
            """)

            completeQuest()
            return false
        } else {
            return step00a()
        }
    }

    override fun completeQuest() {
        logListView.items.add("""
            Well, you got a name I can call you now.

            I expect great things from you, ${player.name}
            """)

    }

}