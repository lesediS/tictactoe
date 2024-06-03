package com.example.tictactoe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoardBase(){
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 1/3, y = 0f),
            end = Offset(x = size.width * 1/3, y = size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 2/3, y = 0f),
            end = Offset(x = size.width * 2/3, y = size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 1/3),
            end = Offset(x = size.width, y = size.height * 1/3)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 2/3),
            end = Offset(x = size.width, y = size.height * 2/3)
        )
    }
}

@Composable
fun Cross() {
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ) {
        drawLine(
            color = Color(204, 0, 0),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
        drawLine(
            color = Color(204, 0, 0),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

@Composable
fun Circle() {
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ) {
        drawCircle(
            color = Color(0, 128, 0),
            style = Stroke(width = 20f)
        )
    }
}

@Composable
fun WinHorizontalLineTop() {
    Canvas(modifier = Modifier.size(300.dp)
    ) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 1/6),
            end = Offset(x = size.width, y = size.height * 1/6)
        )
    }
}

@Composable
fun WinHorizontalLineMid() {
    Canvas(modifier = Modifier.size(300.dp)
    ) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 3/6),
            end = Offset(x = size.width, y = size.height * 3/6)
        )
    }
}

@Composable
fun WinHorizontalLineBott() {
    Canvas(modifier = Modifier.size(300.dp)
    ) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 5/6),
            end = Offset(x = size.width, y = size.height * 5/6)
        )
    }
}

@Composable
fun WinVerticalLineLeft() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 1/6, y = 0f),
            end = Offset(x = size.width * 1/6, y = size.height)
        )
    }
}

@Composable
fun WinVerticalLineMid() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 3/6, y = 0f),
            end = Offset(x = size.width * 3/6, y = size.height)
        )
    }
}

@Composable
fun WinVerticalLineRight() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 5/6, y = 0f),
            end = Offset(x = size.width * 5/6, y = size.height)
        )
    }
}

@Composable
fun WinAcrossLineLeft() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
    }
}

@Composable
fun WinAcrossLineRight() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color(102, 80, 164),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Previews(){
    WinHorizontalLineTop()
}