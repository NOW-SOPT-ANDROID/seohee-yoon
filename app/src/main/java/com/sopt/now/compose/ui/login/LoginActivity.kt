package com.sopt.now.compose.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.sopt.now.compose.data.Key.USER_ID
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.dto.request.RequestLoginDto
import com.sopt.now.compose.data.dto.response.ResponseLoginDto
import com.sopt.now.compose.ui.main.MainActivity
import com.sopt.now.compose.ui.signup.SignUpActivity
import com.sopt.now.compose.ui.signup.SignUpState
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}


@Composable
fun LoginScreen() {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }

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
                    val request = RequestLoginDto(
                        authenticationId = id,
                        password = pw,
                    )
                    val authService by lazy { ServicePool.authService }
                    val liveData = MutableLiveData<LoginState>()

                    authService.login(request).enqueue(object : Callback<ResponseLoginDto> {
                        override fun onResponse(
                            call: Call<ResponseLoginDto>,
                            response: Response<ResponseLoginDto>
                        ) {
                            if (response.isSuccessful) {
                                val data: ResponseLoginDto? = response.body()
                                val userId = response.headers()["location"]

                                val intent = Intent(context, MainActivity::class.java)
                                intent.putExtra(USER_ID, userId)

                                liveData.value = LoginState(
                                    isSuccess = true,
                                    message = "$userId 님 환영합니다!",
                                    userId = userId
                                )

                                Toast.makeText(context, "$userId 님 환영합니다!", Toast.LENGTH_SHORT).show()
                                context.startActivity(intent)
                            } else {
                                val errorBody = response.errorBody()?.string() ?: "No error message"
                                val errorMessage = JSONObject(errorBody).getString("message")

                                liveData.value = LoginState(
                                    isSuccess = false,
                                    message = errorMessage,
                                    userId = null
                                )

                                Toast.makeText(context, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                            liveData.value = LoginState(
                                isSuccess = false,
                                message = "서버에러",
                                userId = null
                            )
                        }
                    })
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