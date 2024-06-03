package com.example.tictactoe

data class States(
    val circleCount: Int = 0,
    val crossCount: Int = 0,
    val drawCount: Int  = 0,
    val hintText: String = "Player 'O' turn",
    val currentTurn: BoardCellValue = BoardCellValue.CIRCLE,
    val victory: VictoryType = VictoryType.NONE,
    val hasWon: Boolean = false
)

enum class BoardCellValue {
    CIRCLE, CROSS, NONE
}

enum class VictoryType {
    HORIZONTAL1, HORIZONTAL2, HORIZONTAL3,
    VERTICAL1, VERTICAL2, VERTICAL3,
    DIAGONAL1, DIAGONAL2,
    NONE
}