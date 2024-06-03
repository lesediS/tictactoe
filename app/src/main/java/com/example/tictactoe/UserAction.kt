package com.example.tictactoe

sealed class UserAction {
    object PlayAgainBtnClicked: UserAction()
    data class CellClicked(val cellNum: Int): UserAction()
}