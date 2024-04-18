package com.sopt.now.compose

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun HomeScreen(){
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    var userNickname by remember { mutableStateOf("") }
    var userMbti by remember { mutableStateOf("") }

    sharedPreferences.apply {
        userNickname = getString("userNickname", userNickname).toString()
        userMbti = getString("userMbti", userMbti).toString()
    }

    LazyColumn {
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Yellow),
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(
                        id = R.drawable.img_mypage_profile),
                    contentDescription = "user_profile"
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = userNickname,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier.padding(20.dp),
                    text = userMbti,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        items(friendList) {
            FriendProfileItem(it)
        }
    }
}

val friendList = listOf<Friend>(
    Friend(
        name = "이의경",
        mbti = "ENFP"
    ),
    Friend(
        name = "공세영",
        mbti = "INTJ"
    ),
    Friend(
        name = "김명석",
        mbti = "ENFP"
    ),
    Friend(
        name = "김아린",
        mbti = "INTJ"
    ),
    Friend(
        name = "김언지",
        mbti = "ISTJ"
    ),
    Friend(
        name = "김윤서",
        mbti = "ESFJ"
    ),
    Friend(
        name = "박효빈",
        mbti = "INFP"
    ),
    Friend(
        name = "배차은우",
        mbti = "INFP"
    ),
    Friend(
        name = "송혜음",
        mbti = "ISFJ"
    ),
    Friend(
        name = "신민석",
        mbti = "ISFJ"
    ),
    Friend(
        name = "이석찬",
        mbti = "ISFJ"
    ),
    Friend(
        name = "이유빈",
        mbti = "ENFP"
    ),
)


@Composable
fun FriendProfileItem(friend: Friend) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(
                id = R.drawable.img_main_profile),
            contentDescription = "friend_profile"
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = friend.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = friend.mbti,
            fontSize = 14.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NOWSOPTAndroidTheme {
        HomeScreen()
    }
}