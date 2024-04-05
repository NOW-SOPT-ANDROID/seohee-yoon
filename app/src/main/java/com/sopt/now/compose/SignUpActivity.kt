package com.sopt.now.compose

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
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
import androidx.compose.material3.SnackbarDuration
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

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUp() {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

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
                text = "Sign Up",
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text="ID",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp)
            )
            TextField(
                value = userId,
                onValueChange = {userId = it},
                label = { Text("아이디를 입력해주세요") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Text(
                text="비밀번호",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp),
            )
            TextField(
                value = password,
                onValueChange = {password = it},
                label = { Text("비밀번호를 입력해주세요") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Text(
                text="닉네임",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp)
            )
            TextField(
                value = nickname,
                onValueChange = {nickname = it},
                label = { Text("닉네임을 입력해주세요") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Text(
                text="MBTI",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp)
            )
            TextField(
                value = mbti,
                onValueChange = {mbti = it},
                label = { Text("MBTI를 입력해주세요") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    when {
                        userId.length !in 6..10 -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                "아이디는 6자 이상 10자 이하여야 합니다",
                                duration = SnackbarDuration.Short)
                            }
                        }
                        password.length !in 8..12 -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                "비밀번호는 8자 이상 16자 이하여야 합니다",
                                duration = SnackbarDuration.Short)
                            }
                        }
                        nickname.isBlank() -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                "닉네임을 입력해주세요",
                                duration = SnackbarDuration.Short)
                            }
                        }
                        else -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                "회원가입 성공",
                                duration = SnackbarDuration.Long)
                            }

                            val intent = Intent(context, LoginActivity::class.java).apply {
                                putExtra("userId", userId)
                                putExtra("password", password)
                                putExtra("nickname", nickname)
                                putExtra("mbti", mbti)
                            }

                            (context as? Activity)?.setResult(RESULT_OK, intent)
                            (context as? Activity)?.finish()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp)) {
                Text(text = "회원가입 하기")
            }
        }
    }
}

