package com.sopt.now.compose

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
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
                    SignUpScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    val context = LocalContext.current

    var userId by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var userNickname by remember { mutableStateOf("") }
    var userMbti by remember { mutableStateOf("") }

    val sharedPreferences:SharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    val edit = sharedPreferences.edit()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
                value = userPassword,
                onValueChange = {userPassword = it},
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
                value = userNickname,
                onValueChange = {userNickname = it},
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
                value = userMbti,
                onValueChange = {userMbti = it},
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
                        userPassword.length !in 8..12 -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                "비밀번호는 8자 이상 16자 이하여야 합니다",
                                duration = SnackbarDuration.Short)
                            }
                        }
                        userNickname.isBlank() -> {
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

                            edit.apply {
                                putString("userId", userId)
                                putString("userPassword", userPassword)
                                putString("userNickname", userNickname)
                                putString("userMbti", userMbti)
                            }

                            edit.apply()

                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)) {
                Text(text = "회원가입 하기")
            }
        }
    }
}

