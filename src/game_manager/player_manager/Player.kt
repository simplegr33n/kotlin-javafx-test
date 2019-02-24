package game_manager.player_manager

class Player {
    var name = "peanut"

    fun setPlayerName(nameToSet: String) {
        if (name == "peanuts") {
            if (nameToSet.length < 20) {
                name = nameToSet
            } else {
                name = nameToSet.substring(0..20)
            }
        } else {
            // send message name already set
        }
    }

}