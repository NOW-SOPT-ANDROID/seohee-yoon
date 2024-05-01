package com.sopt.now.compose.ui.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.data.Friend
import com.sopt.now.compose.data.User

@Composable
fun HomeScreen(user: User) {
    val homeViewModel = HomeViewModel()
    val friendList = homeViewModel.getFriendData()

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
                        id = R.drawable.img_mypage_profile
                    ),
                    contentDescription = "user_profile"
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = user.name,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier.padding(20.dp),
                    text = user.phone,
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
                id = friend.profileImage),
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
            text = friend.phone,
            fontSize = 14.sp,
        )
    }
}