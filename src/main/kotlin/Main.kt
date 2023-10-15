import parser.QuakeLogParser
import report.GameInfoReport

fun main() {
    val games = QuakeLogParser().execute()

    val gameInfoRepost = GameInfoReport()

    games.forEach { game ->
        gameInfoRepost.process(game)
    }
}
