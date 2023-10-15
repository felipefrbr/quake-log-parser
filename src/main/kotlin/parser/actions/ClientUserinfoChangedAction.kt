package parser.actions

import model.Game

class ClientUserinfoChangedAction : GameAction {

    companion object {
        private const val ACTION = "ClientUserinfoChanged"
        private val regex = Regex("^(\\d+) n\\\\(.+)\\\\t\\\\")
    }

    override fun match(action: String): Boolean {
        return ACTION == action
    }

    override fun process(line: String, currentGame: Game?): Game? {
        val matchResult = regex.find(line)
        if (matchResult != null) {
            val (id, name) = matchResult.destructured
            currentGame?.getPlayer(id.toInt())?.changeName(name)
        }
        return currentGame
    }
}
