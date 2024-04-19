package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun MyPageScreen() {
    val context:Context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    val edit = sharedPreferences.edit()

    var userId by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var userNickname by remember { mutableStateOf("") }
    var userMbti by remember { mutableStateOf("") }

    sharedPreferences.apply {
        userId = getString("userId", userId).toString()
        userPassword = getString("userPassword", userPassword).toString()
        userNickname = getString("userNickname", userNickname).toString()
        userMbti = getString("userMbti", userMbti).toString()
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(30.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(
                    id = R.drawable.img_mypage_profile),
                contentDescription = "mypage"
            )
            Text(
                text = userNickname,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Text(
            text = "ID",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = userId,
            fontSize = 20.sp)
        Text(
            text = "비밀번호",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = userPassword,
            fontSize = 20.sp)
        Text(
            text = "MBTI",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = userMbti,
            fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java)

                edit.clear()
                edit.apply()
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = "로그아웃")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    NOWSOPTAndroidTheme {
        MyPageScreen()
    }
}