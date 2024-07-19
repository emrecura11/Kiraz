package com.emrecura.kiraz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emrecura.kiraz.ui.ChatListScreen
import com.emrecura.kiraz.ui.LoginScreen
import com.emrecura.kiraz.ui.ProfileScreen
import com.emrecura.kiraz.ui.SignupScreen
import com.emrecura.kiraz.ui.SingleChatScreen
import com.emrecura.kiraz.ui.SwipeCards
import com.emrecura.kiraz.ui.theme.KirazTheme

sealed class DestinationScreen(val route: String){
    object Signup: DestinationScreen("signup")
    object Login: DestinationScreen("login")
    object Profile: DestinationScreen("profile")
    object Swipe: DestinationScreen("swipe")
    object ChatList: DestinationScreen("chatList")
    object SingleChat: DestinationScreen("singleChat/{chatId}"){

        fun createRoute(id: String)= "singleChat/$id"
    }


}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KirazTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   SwipeAppNavigation()
                }
            }
        }
    }
}

@Composable
fun SwipeAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = DestinationScreen.Swipe.route ){
        composable(DestinationScreen.Signup.route){
            SignupScreen()
        }
        composable(DestinationScreen.Login.route){
            LoginScreen()
        }
        composable(DestinationScreen.Profile.route){
            ProfileScreen()
        }
        composable(DestinationScreen.Swipe.route){
            SwipeCards()
        }
        composable(DestinationScreen.ChatList.route){
            ChatListScreen()
        }
        composable(DestinationScreen.SingleChat.route){
            SingleChatScreen(chatId = "123")
        }
    }
}


