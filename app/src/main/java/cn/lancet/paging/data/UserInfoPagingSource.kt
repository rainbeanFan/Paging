package cn.lancet.paging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.lancet.paging.net.NetWorkUtil

/**
 * Created by Lancet at 2022/8/18 18:18
 */
class UserInfoPagingSource : PagingSource<Int, UserInfo>() {


    override fun getRefreshKey(state: PagingState<Int, UserInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserInfo> {
        return try {
            val currentPage = params.key ?: 1
            val userInfo = NetWorkUtil.instance?.getUserInfo(currentPage, 5)
            val nextPage = if (currentPage < (userInfo?.totalPages ?: 0)) {
                currentPage + 1
            } else {
                null
            }
            LoadResult.Page(
                prevKey = null,
                nextKey = nextPage,
                data = userInfo?.userInfo as List<UserInfo>
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}