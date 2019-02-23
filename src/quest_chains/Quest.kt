package quest_chains

import java.util.concurrent.locks.Condition

abstract class Quest {
    abstract var childQuests: ArrayList<Quest>
    abstract fun conditionalInit(): Boolean

    abstract fun offerString(): String
    abstract fun explainString(): String
    abstract fun acceptText(): String
    abstract fun refuseText(): String
    abstract fun fail(): String

    abstract fun complete()
    abstract fun reward() // reward with XP, gold, resources
}