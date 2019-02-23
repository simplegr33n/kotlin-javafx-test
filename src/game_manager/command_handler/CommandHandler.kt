package game_manager.command_handler

import logListView

lateinit var currentCommand: String
lateinit var listedCommand: List<String>

class CommandHandler {
    fun handleUserCommand(command: String) {
        if (command == "") {
            return
        }
        currentCommand = command

        listedCommand = command.split(" ")

        if (!listedCommand[0].startsWith("/")) {
            logListView.items.add("irm: '$currentCommand'")
            return
        }

        for (cmd in listedCommand) {
            println(cmd)
        }


        when (listedCommand[0]) {
            "/list" -> ListCommand().handleCommand()
            "/l" -> ListCommand().handleCommand()
            "/craft" -> println("Craft things")
            "/c" -> println("Craft things")
            "info" -> println("Info things")
            "/i" -> println("Info things")
            "/sleep" -> println("Sleep to rejuv")
            "/s" -> println("Sleep to rejuv")
            "/exit" -> println("Quit game")
            else -> logListView.items.add("Invalid Command: '$currentCommand'")
        }
    }
}

class ListCommand {
    fun handleCommand() {
        if (listedCommand.size <= 1) {
            logListView.items.add("Try /list [item], like Animals, Items, Features, or All")
            return
        }

        when (listedCommand[1]) {
            "animals" -> logListView.items.add("[Squirrel - 425, 2313]")
            "animal" -> logListView.items.add("[Squirrel - 425, 2313]")
            "items" -> logListView.items.add("No items around...")
            "item" -> logListView.items.add("No items around...")
            "features" -> logListView.items.add("[Mountain - 465, 2540]")
            "feature" -> logListView.items.add("[Mountain - 465, 2540]")
            "all" -> logListView.items.add("Everything, starting with closest:\n[Squirrel - 425, 2313]\n[Mountain - 465, 2540]")
            "*" -> logListView.items.add("Everything, starting with closest:\n[Squirrel - 425, 2313]\n[Mountain - 465, 2540]")
            else -> logListView.items.add("Invalid Command: '$currentCommand'")

        }

    }

}