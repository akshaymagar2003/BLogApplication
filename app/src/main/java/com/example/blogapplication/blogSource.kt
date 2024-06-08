package com.example.blogapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.blogapplication.models.Blog
import retrofit2.HttpException
import java.io.IOException


class BlogSource : PagingSource<Int, Blog>() {

    override fun getRefreshKey(state: PagingState<Int, Blog>): Int?
    {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>):            LoadResult<Int, Blog> {
        return try {
            val nextPage = params.key ?: 1
            val userList = apiService.getPosts(perPage=10,page = nextPage)
            LoadResult.Page(
                data = userList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.isEmpty()) null else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}