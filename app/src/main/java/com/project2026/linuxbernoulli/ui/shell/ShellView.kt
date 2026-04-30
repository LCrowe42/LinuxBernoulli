package com.project2026.linuxbernoulli.ui.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShellView() {
    val viewModel: ShellViewModel = viewModel()
    val lines = viewModel.lines.value
    val input = viewModel.input.value
    val listState = rememberLazyListState()

    // scroll to bottom when new lines are added
    LaunchedEffect(lines.size) {
        if (lines.isNotEmpty()) listState.animateScrollToItem(lines.size - 1)
    }

    val terminalBg = Color(0xFF0D1117)
    val green = Color(0xFF39D353)
    val white = Color(0xFFE6EDF3)
    val gray = Color(0xFF8B949E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(terminalBg)
    ) {
        // Output area
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(lines) { line ->
                Text(
                    text = line.text,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    color = if (line.isInput) green else white,
                    lineHeight = 18.sp
                )
            }
        }

        HorizontalDivider(color = Color(0xFF30363D))

        // Input row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF161B22))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$ ",
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp,
                color = green,
                modifier = Modifier.padding(start = 4.dp, end = 2.dp)
            )
            TextField(
                value = input,
                onValueChange = viewModel::onInputChanged,
                modifier = Modifier.weight(1f),
                textStyle = LocalTextStyle.current.copy(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    color = white
                ),
                placeholder = {
                    Text(
                        "type a command...",
                        fontFamily = FontFamily.Monospace,
                        color = gray,
                        fontSize = 14.sp
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = green
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { viewModel.onSubmit() })
            )
            TextButton(onClick = viewModel::onSubmit) {
                Text("Run", color = green, fontFamily = FontFamily.Monospace)
            }
            TextButton(onClick = viewModel::onClear) {
                Text("Clear", color = gray, fontFamily = FontFamily.Monospace)
            }
        }
    }
}