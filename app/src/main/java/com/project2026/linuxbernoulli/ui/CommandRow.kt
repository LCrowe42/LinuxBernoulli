package com.project2026.linuxbernoulli.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project2026.linuxbernoulli.data.model.Command

@Composable
fun CommandRow (
    command: Command,
    onDelete: (Command) -> Unit,
    onToggle: (Command) -> Unit,
    onSelectCommand: (Command) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onSelectCommand(command) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.weight(1.5f)
            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Name:", modifier = Modifier.weight(1.0f))
                    Text(command.name, modifier = Modifier.weight(2.0f), fontSize = 28.sp, color = MaterialTheme.colorScheme.secondary)
                }
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Description:", modifier = Modifier.weight(1.0f))
                    Text(command.description, modifier = Modifier.weight(2.0f))
                }
            }
            Column(
                modifier = Modifier.weight(1.0f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick= { onDelete(command) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Delete")
                }
                Spacer(modifier = Modifier.padding(bottom=5.dp))
                Row() {
                    Checkbox(checked = command.favorite, onCheckedChange = { onToggle(command) }, modifier = Modifier.padding(end=5.dp))
                    Text("Favorite")
                }
            }
        }
    }
}