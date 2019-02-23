import game_manager.command_handler.CommandHandler
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



lateinit var window: Stage

lateinit var logListView: ListView<String>
lateinit var enterTextField: TextField
lateinit var enterButton: Button

var awaitingInput = null


class Main : Application() {
    override fun start(primaryStage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass.getResource("main.fxml"))
        window = primaryStage
        window.title = "Nature Versus Origin"
        window.scene = Scene(root, 800.0, 600.0)

        setUserAgentStylesheet(STYLESHEET_CASPIAN)

        // LIST VIEW
        logListView = window.scene.lookup("#log_listview") as ListView<String>
        logListView.isMouseTransparent = true
        logListView.isFocusTraversable = false
        val textList: ObservableList<String> = FXCollections.observableArrayList("Welcome to Nature Versus Origin!", "The first text you enter will become your name, so choose wisely...")
        logListView.items.addAll(textList)

        logListView.items.addListener(ListChangeListener<Any> { c -> logListView.scrollTo(c.list.size - 1) })


        // TEXT FIELD
        enterTextField = window.scene.lookup("#input_textfield") as TextField
        enterTextField.setOnAction { enterPressed(enterTextField.text) }


        // ENTER BUTTON
        enterButton = window.scene.lookup("#enter_button") as Button
        enterButton.setOnAction { enterPressed(enterTextField.text) }

        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}


fun enterPressed(msg: String) {
    if (msg.isEmpty()) {
        return
    }
    println(msg.length)
    if (awaitingInput != null) {

    } else {
        CommandHandler().handleUserCommand(msg)
    }

    enterTextField.clear()
}
