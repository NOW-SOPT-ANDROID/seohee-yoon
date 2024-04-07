package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
                    Main(
                        intent.getStringExtra("userId"),
                        intent.getStringExtra("password"),
                        intent.getStringExtra("nickname"),
                        intent.getStringExtra("mbti")
                    )
                }
            }
        }
    }
}


@Composable
fun Main(userId: String?, password: String?, nickname: String?, mbti: String?) {
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
                text = "$nickname",
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
            text = "$password",
            fontSize = 20.sp)
        Text(
            text = "MBTI",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "$mbti",
            fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NOWSOPTAndroidTheme {
        Main("userId", "password", "nickname", "mbti")
    }
}