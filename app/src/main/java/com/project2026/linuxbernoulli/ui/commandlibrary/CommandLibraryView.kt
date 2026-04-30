package com.project2026.linuxbernoulli.ui.commandlibrary

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project2026.linuxbernoulli.data.model.Command
import com.project2026.linuxbernoulli.ui.CommandCard

@ExperimentalFoundationApi
@Composable
fun CommandLibraryView(
    selectedCommand: Command? = null,
    onDelete: () -> Unit,
    onToggle: (Command) -> Unit,
    onSelectCommand: (Command) -> Unit,
    waiting: Boolean = false,
    dialog: CommandLibraryViewModel.ShellDialog,
    modifier: Modifier = Modifier
) {
    val viewModel: CommandLibraryViewModel = viewModel()
    val commands = viewModel.commands.value
    val searchQuery = viewModel.searchQuery.value
    Box(
        contentAlignment = Alignment.Center
    ) {
        if(dialog.showDialog.value) {
            ShellDialog(
                onDismiss = dialog::hideDialog,
                onDelete = onDelete
            )
        }
        val alpha = when(waiting) {
            true -> 0.2f
            false -> 1.0f
        }
        val configuration = LocalConfiguration.current
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // landscape
            Landscape(
                commands = commands,
                searchQuery = searchQuery,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                selectedCommand = selectedCommand,
                onDelete = dialog::showDialog,
                onToggle = onToggle,
                onSelectCommand = onSelectCommand,
                modifier = modifier.alpha(alpha)
            )
        } else {
            // portrait
            Portrait(
                commands = commands,
                searchQuery = searchQuery,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                onDelete = dialog::showDialog,
                onToggle = onToggle,
                onSelectCommand = onSelectCommand,
                modifier = modifier.alpha(alpha),
            )
        }
        if(waiting) {
            CircularProgressIndicator()
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShellDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { TextButton(onClick = { onDelete(); onDismiss() }) { Text("Open") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel")} },
        text = {
            Text("Open Shell?", style = MaterialTheme.typography.titleLarge)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CommandLibraryUI(
    modifier: Modifier = Modifier,
    commands: List<Command>,
    onDelete: (Command) -> Unit,
    onToggle: (Command) -> Unit,
    onSelectCommand: (Command) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(commands) { command ->
            CommandCard(command, onDelete, onToggle, onSelectCommand)
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text("Search commands...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        singleLine = true
    )
}

@Composable
fun Portrait(
    modifier: Modifier = Modifier,
    commands: List<Command>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onDelete: (Command) -> Unit,
    onToggle: (Command) -> Unit,
    onSelectCommand: (Command) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChanged = onSearchQueryChanged
        )
        CommandLibraryUI(
            commands = commands,
            onDelete = onDelete,
            onToggle = onToggle,
            onSelectCommand = onSelectCommand
        )
    }
}

@Composable
fun Landscape(
    modifier: Modifier = Modifier,
    commands: List<Command>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    selectedCommand: Command? = null,
    onDelete: (Command) -> Unit,
    onToggle: (Command) -> Unit,
    onSelectCommand: (Command) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.weight(1.0f).padding(16.dp),

            ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(selectedCommand?.name ?: "")
            }
        }
        SearchBar(
            query = searchQuery,
            onQueryChanged = onSearchQueryChanged
        )
        CommandLibraryUI(
            modifier = Modifier.weight(1.0f),
            commands = commands,
            onDelete = onDelete,
            onToggle = onToggle,
            onSelectCommand = onSelectCommand
        )
    }
}