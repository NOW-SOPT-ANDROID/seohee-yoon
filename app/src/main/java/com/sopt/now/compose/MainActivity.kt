package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        intent.getStringExtra("userId"),
                        intent.getStringExtra("userPw"),
                        intent.getStringExtra("userNickname"),
                        intent.getStringExtra("userMbti")
                    )
                }
            }
        }
    }
}


@Composable
fun MainScreen(userId: String?, userPw: String?, userNickname: String?, userMbti: String?) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(30.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(
                    id = R.drawable.ic_launcher_background),
                    contentDescription = "profile"
            )
            Text(
                text = "$userNickname",
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
            text = "$userId",
            fontSize = 20.sp)
        Text(
            text = "비밀번호",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "$userPw",
            fontSize = 20.sp)
        Text(
            text = "MBTI",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "$userMbti",
            fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NOWSOPTAndroidTheme {
        MainScreen("userId", "userPw", "userNickname", "userMbti")
    }
}