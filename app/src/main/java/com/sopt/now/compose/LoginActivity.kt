package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = intent.getParcelableExtra<User>("user")
                    LoginScreen(user ?: User("", "", "", ""))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(user:User) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                text = "ID",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp)
            )
            TextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("아이디를 입력해주세요") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Text(
                text = "비밀번호",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp)
            )
            TextField(
                value = pw,
                onValueChange = { pw = it },
                label = { Text("비밀번호를 입력해주세요") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (user.id == id && user.password == pw && user.id.isNotEmpty() && user.password.isNotEmpty()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("로그인 성공")
                        }
                        val intent = Intent(context, MainActivity::class.java)
                        intent.putExtra("user", user)
                        context.startActivity(intent)
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("아이디 또는 비밀번호가 일치하지 않습니다")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(text = "로그인 하기")
            }

            Button(
                onClick = {
                    context.startActivity(Intent(context, SignUpActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(text = "회원가입 하기")
            }
        }
    }
}