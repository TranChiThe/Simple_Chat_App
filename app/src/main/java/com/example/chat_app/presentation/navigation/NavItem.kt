package com.example.chat_app.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chat_app.R


data class NavItem(
    val label: String,
    val icon: Int,
    val selectedColor: Color,
    val unselectedColor: Color,
    val route: String
)

val bottomNavItems = listOf(
    NavItem("Search", R.drawable.search_alt_svgrepo_com, Color(0xFF1E88E5), Color(0xFF9E9E9E), "search"),
    NavItem("Chat", R.drawable.message_square_chat_svgrepo_com, Color(0xFF1E88E5), Color(0xFF9E9E9E), "chat"),
    NavItem("Friend", R.drawable.friend_svgrepo_com, Color(0xFF1E88E5), Color(0xFF9E9E9E), "friend"),
    NavItem("Personal", R.drawable.person_button_svgrepo_com, Color(0xFF1E88E5), Color(0xFF9E9E9E), "personal")
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    if(currentRoute == "login" || currentRoute == "register" || currentRoute == "welcome") return
    Column {
        Divider(color = Color.Gray, thickness = 1.dp)
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 8.dp
        ) {
            bottomNavItems.forEach { item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo("chat") {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.label,
                            tint = if (isSelected) item.selectedColor else item.unselectedColor,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontSize = 15.sp
                        )
                    }
                )
            }
        }
    }
}