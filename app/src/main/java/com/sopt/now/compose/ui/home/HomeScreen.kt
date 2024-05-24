package com.sopt.now.compose.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.sopt.now.compose.R
import com.sopt.now.compose.data.Friend
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.dto.response.ResponseFriendDto
import com.sopt.now.compose.data.dto.response.ResponseUserDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen(userId: String, viewModel: HomeViewModel = viewModel()) {
    val userData = viewModel.userData.value
    val friendList = viewModel.friendList.value

    LaunchedEffect(Unit) {
        getFriendData(viewModel)
        viewModel.getUserData(userId)
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
                        id = R.drawable.img_mypage_profile
                    ),
                    contentDescription = "user_profile"
                )

                Spacer(modifier = Modifier.width(10.dp))

                if (userData != null) {
                    Text(
                        text = userData.nickname,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                if (userData != null) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = userData.phone,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

        items(friendList) { friend ->
            FriendProfileItem(friend)
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
            painter = rememberImagePainter(
                data = friend.profileImage,
                builder = {
                    transformations(CircleCropTransformation()) // optional: apply circle crop transformation
                }
            ),
            contentDescription = "friend_profile",
            modifier = Modifier.size(50.dp)
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

class HomeViewModel : ViewModel() {
    val userData: MutableState<ResponseUserDto.UserData?> = mutableStateOf(null)
    val friendList: MutableState<List<Friend>> = mutableStateOf(emptyList())

    fun getUserData(userId: String) {
        ServicePool.userService.getUser(userId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    userData.value = data?.data

                    Log.d("MyPageViewModel", "User data: ${userData.value}")
                } else {
                    Log.d("MyPageViewModel", "Failed to load user data: $userId")
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Log.d("MyPageViewModel", "Failed to load user data: ${t.message}")
            }
        })
    }
}

fun getFriendData(viewModel: HomeViewModel) {
    if (viewModel.friendList.value.isEmpty()) {
        ServicePool.friendService.getFriend(2).enqueue(object : Callback<ResponseFriendDto> {
            override fun onResponse(
                call: Call<ResponseFriendDto>,
                response: Response<ResponseFriendDto>
            ) {
                if(response.isSuccessful) {
                    val friend = response.body()?.data

                    friend?.forEach { friendData ->
                        viewModel.friendList.value += Friend(
                            friendData.avatar, friendData.firstName, friendData.email
                        )
                    }
                } else {
                    Log.d("HomeViewModel", "Failed to load user data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                Log.d("HomeViewModel", "Load friend data error : ${t.message}")
            }
        })
    }
}