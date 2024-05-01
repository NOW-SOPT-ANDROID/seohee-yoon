package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyPageScreen(user: User) {
    val context: Context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.img_mypage_profile
                ),
                contentDescription = "mypage"
            )
            Text(
                text = user.name,
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
            text = user.id,
            fontSize = 20.sp
        )
        Text(
            text = "비밀번호",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = user.password,
            fontSize = 20.sp
        )
        Text(
            text = "전화번호",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = user.phone,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, LoginActivity::class.java))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "로그아웃")
        }
    }
}