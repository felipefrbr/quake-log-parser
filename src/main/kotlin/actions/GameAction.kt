package actions

import model.Game

interface GameAction {
    fun match(action: String): Boolean
    fun process(line: String, currentGame: Game?): Game?
}
