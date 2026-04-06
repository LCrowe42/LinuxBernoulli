package com.project2026.linuxbernoulli

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import com.project2026.linuxbernoulli.ui.theme.LinuxBernoulliTheme
import org.json.JSONObject
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LinuxBernoulliTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    ) {
                        Main()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Text(
                            text = "Linux Bernoulli",
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton( onClick = { println("") } ) {
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
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
            paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CotD()

            Spacer(modifier = Modifier.height(20.dp))

            CommandLibrary()

            Spacer(modifier = Modifier.height(20.dp))

            Favorites(favorites)

            FakeShell()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CotD() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Command of the Day",
                style = MaterialTheme.typography.titleMedium
            )
            /* TODO: modify referenced function below for database connection */
            Text (
                text = cotdSelect(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
//placeholder function to be updated with database connection
@RequiresApi(Build.VERSION_CODES.O)
fun cotdSelect(): String{
    val commands = mapOf(
        "echo" to "Displays a line of text or variable value to the terminal",
        "ls" to "Lists directory contents",
        "mkdir" to "Creates a new directory",
        "pwd" to "Prints the current working directory",
        "df" to "Reports disk space usage of file systems",
        "top" to "Displays real-time system resource usage and running processes",
        "ps" to "Shows a snapshot of currently running processes",
        "uname" to "Prints system information such as kernel name and version",
        "uptime" to "Shows how long the system has been running",
        "free" to "Displays memory and swap usage",
        "hostname" to "Shows or sets the system hostname",
        "cat" to "Concatenates and displays file contents",
        "netstat" to "Displays network connections and routing tables",
        "du" to "Estimates file and directory disk usage",
        "find" to "Searches for files in a directory hierarchy",
        "tail" to "Outputs the last part of a file"
    )

    val today = LocalDate.now()
    val seed = today.year * 10000 + today.monthValue * 100 + today.dayOfMonth

    val (command, description) = commands.entries.toList()[Random(seed).nextInt(commands.size)]


    return $$"Command: %1$s\nWhat it Does: %2$s".format(
        command,
        description
    )
}

@Composable
fun CommandLibrary() {
    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Library of Commands",
                style = MaterialTheme.typography.titleMedium
            )
            /* TODO: intent to move to library page */
        }
    }
}

@Composable
fun Favorites(favorites: List<String>) {
    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Favorite Commands",
                style = MaterialTheme.typography.titleMedium
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(favorites) { command ->
                    Card(
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Text(
                            text = command,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FakeShell() {
    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Shell Simulator",
                style = MaterialTheme.typography.titleMedium
            )
            /* TODO: intent to move to library page */
        }
    }
}



val favorites = listOf("ls", "pwd", "cat", "grep", "find", "tail", "df", "top")


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LinuxBernoulliTheme {
        Main()
    }
}