package com.project2026.linuxbernoulli

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project2026.linuxbernoulli.ui.nav.CommandLibrary
import com.project2026.linuxbernoulli.ui.nav.CommandsNavGraph
import com.project2026.linuxbernoulli.ui.nav.Favorites
import com.project2026.linuxbernoulli.ui.nav.Home

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val nav = rememberNavController()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) { pv: PaddingValues ->
        CommandsNavGraph(navController = nav, modifier = Modifier.padding(pv))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = "Linux Bernoulli",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Image(
                    painter = painterResource(id = R.drawable.terminalicon),
                    contentDescription = "Terminal Icon",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Composable
private fun BottomBar(
    nav: NavHostController
) {
    val backStackEntry by nav.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    NavigationBar() {
        NavigationBarItem(
            selected = currentDestination?.hasRoute(CommandLibrary::class) ?: false,
            onClick = {
                nav.navigate(CommandLibrary) {
                    launchSingleTop = true
                    popUpTo(CommandLibrary)
                }
            },
            icon = {
                Icon(painterResource(id= R.drawable.terminalicon), "")
            },
            label = {
                Text("Command Library")
            }
        )
        NavigationBarItem(
            selected = currentDestination?.hasRoute(Home::class) ?: false,
            onClick = {
                nav.navigate(Home) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(painterResource(R.drawable.home), "")
            },
            label = {
                Text("Home")
            }
        )
        NavigationBarItem(
            selected = currentDestination?.hasRoute(Favorites::class) ?: false,
            onClick = {
                nav.navigate(Favorites) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(painterResource(R.drawable.terminalicon), "")
            },
            label = {
                Text("Favorites")
            }
        )
    }
}