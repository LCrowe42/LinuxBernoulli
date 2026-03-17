package com.project2026.linuxbernoulli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project2026.linuxbernoulli.ui.theme.LinuxBernoulliTheme

class MainActivity : ComponentActivity() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Image(
                            painter = painterResource(id = R.drawable.terminalicon),
                            contentDescription = "Terminal Icon",
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
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
                }
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
            // TODO: Command of the day

            Spacer(modifier = Modifier.height(20.dp))

            // TODO: Command Library Card

            //Spacer?

            // TODO: Favorited commands card

            // TODO: Fake shell card
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LinuxBernoulliTheme {
        Main()
    }
}