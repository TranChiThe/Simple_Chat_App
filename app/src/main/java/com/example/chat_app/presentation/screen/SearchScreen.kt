package com.example.chat_app.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.chat_app.R
import com.example.chat_app.presentation.view_model.UserViewModel
import androidx.compose.material3.Icon as Icon

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel()
) {

    val users by userViewModel.userFlow.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Search...") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_alt_svgrepo_com),
                            contentDescription = "Search",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    textStyle = TextStyle(fontSize = 14.sp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon (
                    painter = painterResource(id = R.drawable.qr_code_scan_svgrepo_com),
                    contentDescription = "Menu",
                    tint = Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp)
            ) {
                items(users){user->
                    Text(text = user.password.toString())
                }
            }
        }
    }
}
