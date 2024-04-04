package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    LoginScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = "Welcome to SOPT",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
            )
        Text(
            text="ID",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 30.dp)
        )
        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("아이디를 입력해주세요") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        )
        Text(
            text="비밀번호",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 30.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호를 입력해주세요") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)) {
            Text(text = "로그인 하기")
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)) {
            Text(text = "회원가입 하기")
        }
    }
}