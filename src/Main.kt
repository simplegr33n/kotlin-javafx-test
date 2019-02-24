import game_manager.command_handler.CommandHandler
import game_manager.player_manager.Player
import game_manager.quest_manager.Quest
import game_manager.quest_manager.SetNameQuest
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage
import javafx.scene.control.ListView;
import javafx.scene.control.TextField
import javafx.collections.ListChangeListener
import javafx.scene.control.Label


lateinit var window: Stage

lateinit var logListView: ListView<String>
lateinit var enterTextField: TextField
lateinit var enterButton: Button

lateinit var questList: ArrayList<Quest>
lateinit var currentQuest: Quest

lateinit var player: Player

var awaitingInput = null

var shouldReturnResponse = false
var shouldStillCommandHandle = false
var returnedResponse = ""


class Main : Application() {
    override fun start(primaryStage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass.getResource("/interface/main.fxml"))
        window = primaryStage
        window.title = "Nature Versus Origin"
        window.scene = Scene(root, 800.0, 600.0)

        // TODO: proper custom theme
        setUserAgentStylesheet(STYLESHEET_CASPIAN)

        // Create Player
        player = Player()


        // List of Quests
        questList = arrayListOf(SetNameQuest())

        val playerNameLabel: Label = window.scene.lookup("#name_label") as Label
        playerNameLabel.text = player.name

        // LIST VIEW
        logListView = window.scene.lookup("#log_listview") as ListView<String>
        logListView.isMouseTransparent = true
        logListView.isFocusTraversable = false
        val textList: ObservableList<String> = FXCollections.observableArrayList("Welcome to Nature Versus Origin!", "The first text you enter will become your name, so choose wisely...")
        logListView.items.addAll(textList)
        // keep to botttom of list
        logListView.items.addListener(ListChangeListener<Any> { c -> logListView.scrollTo(c.list.size - 1) })


        // TEXT FIELD
        enterTextField = window.scene.lookup("#input_textfield") as TextField
        enterTextField.setOnAction { enterPressed(enterTextField.text) }


        // ENTER BUTTON
        enterButton = window.scene.lookup("#enter_button") as Button
        enterButton.setOnAction { enterPressed(enterTextField.text) }

        primaryStage.show()

        println("dog")

        currentQuest = questList[0]
        currentQuest.isActive = true
        shouldReturnResponse = currentQuest.nextStep()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}


fun enterPressed(msg: String) {
    enterTextField.clear()
    if (msg.isEmpty()) {
        return
    }

    if (!shouldReturnResponse || shouldStillCommandHandle){
        CommandHandler().handleUserCommand(msg)
    }
    if (shouldReturnResponse) {
        returnedResponse = msg
        currentQuest.nextStep()
    }



}
