package com.example.blogapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.blogapplication.models.Blog
import com.example.blogapplication.ui.theme.BlogApplicationTheme
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    private val postViewModel: PostViewModel by viewModels()
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("higgg","hello")
        Log.d("higgg","hello2")
       setContent {
           BlogApplicationTheme {
               UserList(viewModel = postViewModel, context = this)
           }
       }
    }
}

@Composable
fun UserList(modifier: Modifier = Modifier, viewModel: PostViewModel, context: Context) {
    UserInfoList(modifier, userList = viewModel.user, context)
}

@Composable
fun UserInfoList(modifier: Modifier, userList: Flow<PagingData<Blog>>, context: Context) {
    val userListItems: LazyPagingItems<Blog> =            userList.collectAsLazyPagingItems()

    LazyColumn {
        items(userListItems) { item ->
            item?.let {
                EmployeeItem(empData = it, context = context)
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}
@Composable
fun EmployeeItem(empData: Blog,  context: Context) {
    Card(
        modifier = Modifier
            .padding(bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp)
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, WebViewActivity::class.java).apply {
                    putExtra("URL", empData.link)
                }
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {


            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = empData.title?.rendered!!,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Black
                )
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = "Author Id-${empData.author}  ",
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 25.dp)
                    )
                }
            }

    }
}
