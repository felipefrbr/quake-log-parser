package parser

import model.Game
import parser.actions.ClientConnectAction
import parser.actions.ClientUserinfoChangedAction
import parser.actions.InitGameAction
import parser.actions.KillAction
import parser.actions.ShutdownGameAction

class QuakeLogParser() {

    private val games = mutableListOf<Game>()

    private var currentGame: Game? = null

    private val regex = Regex("^(\\d+:\\d+) (\\S+): ?(.+)?")

    private val actions = listOf(
        InitGameAction(),
        ShutdownGameAction(),
        ClientUserinfoChangedAction(),
        ClientConnectAction(),
        KillAction(),
    )

    fun execute(): List<Game> {
        val reader = Thread.currentThread().contextClassLoader.getResourceAsStream("quake.log")?.bufferedReader()
            ?: throw Exception("File not found")

        reader.use {
            var line = it.readLine()
            while (line != null) {
                process(line.trim())
                line = it.readLine()
            }
        }

        return games
    }

    private fun process(line: String) {
        val matchResult = regex.find(line)
        if (matchResult != null) {
            val (time, action, message) = matchResult.destructured

            actions.firstOrNull { it.match(action) }?.process(message, currentGame)?.let { game ->
                currentGame = game
                if (!game.isRunning()) {
                    games.add(game)
                }
            }
        }
    }
}
