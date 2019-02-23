package game_manager.log_manager

import logListView


class LogHandler {

    var EXPECT_RETURN: Boolean = false






    fun addItem(item: String) {
        logListView.items.add(item)
    }


}