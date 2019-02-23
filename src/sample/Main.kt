package sample

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

lateinit var window: Stage

lateinit var logListView: ListView<String>
lateinit var enterTextField: TextField
lateinit var enterButton: Button


public class Main : Application() {
    override fun start(primaryStage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass.getResource("main.fxml"))
        window = primaryStage
        window.title = "Hello World"
        window.scene = Scene(root, 800.0, 600.0)

        // LISTVIEW
        logListView = window.scene.lookup("#log_listview") as ListView<String>
        val textList: ObservableList<String> = FXCollections.observableArrayList("Cool", "Dot", "Com")
        logListView.items.addAll(textList)


        // TEXTFIELD
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
    logListView.items.add(msg)
    enterTextField.clear()
}
