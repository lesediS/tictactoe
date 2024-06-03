package com.example.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {

    var state by mutableStateOf(States())

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE
    )

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.CellClicked -> {
                addValueToBoard(action.cellNum)
            }
            UserAction.PlayAgainBtnClicked -> {
                gameReset()
            }
        }
    }

    private fun gameReset() {
        boardItems.forEach { (i, _) ->
            boardItems[i] = BoardCellValue.NONE
        }
        state = state.copy(
            hintText = "Player 'O' turn",
            currentTurn = BoardCellValue.CIRCLE,
            victory = VictoryType.NONE,
            hasWon = false
        )
    }

    private fun addValueToBoard(cellNum: Int) {
        if(boardItems[cellNum] != BoardCellValue.NONE) {
            return
        }
        if(state.currentTurn == BoardCellValue.CIRCLE) {
            boardItems[cellNum] = BoardCellValue.CIRCLE
            if (checkForWin(BoardCellValue.CIRCLE)){
                state = state.copy (
                    hintText = "Player 'O' won!",
                    circleCount = state.circleCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else
            state = if (hasBoardFull()) {
                state.copy(
                    hintText = "Draw! Try again?",
                    drawCount = state.drawCount + 1
                )
            } else {
                state.copy(
                    hintText = "Player 'O' turn",
                    currentTurn = BoardCellValue.CROSS
                )
            }

        } else if (state.currentTurn == BoardCellValue.CROSS) {
            boardItems[cellNum] = BoardCellValue.CROSS
            if (checkForWin(BoardCellValue.CROSS)){
                state = state.copy (
                    hintText = "Player 'X' won!",
                    crossCount = state.crossCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else
                state = if (hasBoardFull()) {
                    state.copy(
                        hintText = "Draw! Try again?",
                        drawCount = state.drawCount + 1
                    )
                } else {
                    state.copy(
                        hintText = "Player 'X' turn",
                        currentTurn = BoardCellValue.CIRCLE
                    )
                }
        }
    }

    private fun checkForWin(boardVal: BoardCellValue): Boolean {
        when {
            boardItems[1] == boardVal && boardItems[2] == boardVal && boardItems[3] == boardVal -> {
                state = state.copy(victory = VictoryType.HORIZONTAL1)
                return true
            }

            boardItems[4] == boardVal && boardItems[5] == boardVal && boardItems[6] == boardVal -> {
                state = state.copy(victory = VictoryType.HORIZONTAL2)
                return true
            }

            boardItems[7] == boardVal && boardItems[8] == boardVal && boardItems[9] == boardVal -> {
                state = state.copy(victory = VictoryType.HORIZONTAL3)
                return true
            }

            boardItems[1] == boardVal && boardItems[4] == boardVal && boardItems[7] == boardVal -> {
                state = state.copy(victory = VictoryType.VERTICAL1)
                return true
            }

            boardItems[2] == boardVal && boardItems[5] == boardVal && boardItems[8] == boardVal -> {
                state = state.copy(victory = VictoryType.VERTICAL2)
                return true
            }

            boardItems[3] == boardVal && boardItems[6] == boardVal && boardItems[9] == boardVal -> {
                state = state.copy(victory = VictoryType.VERTICAL3)
                return true
            }

            boardItems[1] == boardVal && boardItems[5] == boardVal && boardItems[9] == boardVal -> {
                state = state.copy(victory = VictoryType.DIAGONAL1)
                return true
            }

            boardItems[3] == boardVal && boardItems[5] == boardVal && boardItems[7] == boardVal -> {
                state = state.copy(victory = VictoryType.DIAGONAL2)
                return true
            }

            else -> return false
        }
    }

    private fun hasBoardFull(): Boolean {
        return !boardItems.containsValue(BoardCellValue.NONE)
//        if (boardItems.containsValue(BoardCellValue.NONE)) return false
//        return true
    }
}