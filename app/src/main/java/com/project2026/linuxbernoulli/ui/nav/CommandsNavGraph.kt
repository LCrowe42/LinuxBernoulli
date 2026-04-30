package com.project2026.linuxbernoulli.ui.nav

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ComponentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project2026.linuxbernoulli.MainActivity
import com.project2026.linuxbernoulli.data.model.Command
import com.project2026.linuxbernoulli.ui.commandlibrary.CommandLibraryView
import com.project2026.linuxbernoulli.ui.commandlibrary.CommandLibraryViewModel
import com.project2026.linuxbernoulli.ui.favorites.FavoritesView
import com.project2026.linuxbernoulli.ui.favorites.FavoritesViewModel
import com.project2026.linuxbernoulli.ui.home.HomeView
import com.project2026.linuxbernoulli.ui.home.HomeViewModel

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalFoundationApi
@Composable
fun CommandsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<CommandLibrary> {
            val commandsListViewModel: CommandLibraryViewModel = viewModel(viewModelStoreOwner = LocalActivity.current as MainActivity)
            val commandList by commandsListViewModel.commands
            val selectedCommand by commandsListViewModel.selectedCommand
            CommandLibraryView(
                commands = commandList,
                selectedCommand = selectedCommand,
                onDelete = commandsListViewModel::deleteCommand,
                onToggle = commandsListViewModel::toggleFavorite,
                onSelectCommand = commandsListViewModel::selectCommand,
                waiting = commandsListViewModel.waiting.value,
                dialog = commandsListViewModel.dialog
            )
        }
        composable<Favorites> {
            val commandsListViewModel: FavoritesViewModel = viewModel(viewModelStoreOwner = LocalActivity.current as MainActivity)
            FavoritesView(
                onDelete = commandsListViewModel::deleteCommand,
                onToggle = commandsListViewModel::toggleFavorite,
                onSelectCommand = commandsListViewModel::selectCommand,
                dialog = commandsListViewModel.dialog
            )
        }
        composable<Home> {
            HomeView()
        }
    }
}