package parser

import actions.ClientConnectAction
import actions.ClientUserinfoChangedAction
import actions.InitGameAction
import actions.ShutdownGameAction
import model.Game

class QuakeLogParser() {

    private val games = mutableListOf<Game>()

    private var currentGame: Game? = null

    private val regex = Regex("^(\\d+:\\d+) (.+): ?(.+)?")

    private val actions = listOf(
        InitGameAction(),
        ShutdownGameAction(),
        ClientUserinfoChangedAction(),
        ClientConnectAction(),
    )

    fun execute() {
        val reader = Thread.currentThread().contextClassLoader.getResourceAsStream("quake.log")?.bufferedReader()
            ?: throw Exception("File not found")

        reader.use {
            var line = it.readLine()
            while (line != null) {
                process(line.trim())
                line = it.readLine()
            }
        }

        games.forEach {
            println(it)
        }
    }

    private fun process(line: String) {
        val matchResult = regex.find(line)
        if (matchResult != null) {
            val (time, action, message) = matchResult.destructured
            // println("time: $time, action: $action, message: $message")

            actions.firstOrNull { it.match(action) }?.process(message, currentGame)?.let { game ->
                println("result: $game")
                currentGame = game
                if (!game.isRunning()) {
                    games.add(game)
                }
            }
        }
    }
}
