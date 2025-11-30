package com.example.app_ticket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.app_ticket.Screen.MovieNavGraph
import com.example.app_ticket.ui.theme.App_TicketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            App_TicketTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieNavGraph()
                }
            }
        }
    }

}
