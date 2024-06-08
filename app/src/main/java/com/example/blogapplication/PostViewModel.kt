package com.example.blogapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.blogapplication.models.Blog
import kotlinx.coroutines.flow.Flow

//class PostViewModel(private val repository: PostRepository) : ViewModel() {
//    val pager = Pager(
//        config = PagingConfig(pageSize = 10),
//        pagingSourceFactory = { repository.getPosts() }
//    ).flow.cachedIn(viewModelScope)
//}
class PostViewModel : ViewModel() {
    val user: Flow<PagingData<Blog>> = Pager(PagingConfig(pageSize = 10)) {
       BlogSource()
    }.flow.cachedIn(viewModelScope)
}

