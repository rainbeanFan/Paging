package cn.lancet.paging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import cn.lancet.paging.data.UserInfoPagingSource

/**
 * Created by Lancet at 2022/8/18 18:28
 */
class UserInfoViewModel : ViewModel() {

    fun getUserInfo() = Pager(
        PagingConfig(pageSize = 3)
    ) {
        UserInfoPagingSource()
    }.flow

}