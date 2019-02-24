package game_manager.print_manager

abstract class Event {
    abstract var childEvents: ArrayList<Event>

    abstract fun initConditions(): Boolean

    abstract fun offerString(): String
    abstract fun explainString(): String
    abstract fun acceptText(): String
    abstract fun refuseText(): String
    abstract fun fail(): String

    abstract fun complete()
    abstract fun reward() // reward with XP, gold, resources
}