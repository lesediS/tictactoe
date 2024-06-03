package com.example.tictactoe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.GrayBackground
import com.example.tictactoe.ui.theme.Purple40

@Composable
fun GameScreen(
    viewModel: ViewModel
) {
    val state = viewModel.state

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Player 'O': ${state.circleCount}", fontSize = 16.sp)
            Text(text = "Draw: ${state.drawCount}", fontSize = 16.sp)
            Text(text = "Player 'X': ${state.crossCount}", fontSize = 16.sp)
        }
        Text(text = "3 In A Row",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = Purple40)

        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(18.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(GrayBackground),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f),
                columns = GridCells.Fixed(3)
            ) {
                viewModel.boardItems.forEach{ (cellNum, boardCellValue) ->
                    item {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    viewModel.onAction(UserAction.CellClicked(cellNum))
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            AnimatedVisibility(
                                visible = viewModel.boardItems[cellNum] != BoardCellValue.NONE,
                                enter = scaleIn(tween(300))
                            ) {
                                if (boardCellValue == BoardCellValue.CIRCLE) {
                                    Circle()
                                } else if (boardCellValue == BoardCellValue.CROSS) {
                                    Cross()
                                }
                            }

                        }
                    }
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(300))
                ) {
                    DrawWinnerLine(state = state)
                }
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = state.hintText,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal
            )
            Button(
                onClick = {
                    viewModel.onAction(
                        UserAction.PlayAgainBtnClicked
                    )
                },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
               Text(text = "Play Again", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DrawWinnerLine(
    state: States
) {
    when (state.victory){
        VictoryType.HORIZONTAL1 -> WinHorizontalLineTop()
        VictoryType.HORIZONTAL2 -> WinHorizontalLineMid()
        VictoryType.HORIZONTAL3 -> WinHorizontalLineBott()
        VictoryType.VERTICAL1 -> WinVerticalLineLeft()
        VictoryType.VERTICAL2 -> WinVerticalLineMid()
        VictoryType.VERTICAL3 -> WinVerticalLineRight()
        VictoryType.DIAGONAL1 -> WinAcrossLineLeft()
        VictoryType.DIAGONAL2 -> WinAcrossLineRight()
        VictoryType.NONE -> {}
    }
}

@Preview
@Composable
fun Prev() {
    GameScreen(
        viewModel = ViewModel()
    )
}