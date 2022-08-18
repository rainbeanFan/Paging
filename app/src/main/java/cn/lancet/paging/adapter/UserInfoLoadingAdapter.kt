package cn.lancet.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.lancet.paging.R

/**
 * Created by Lancet at 2022/8/18 21:51
 */
class UserInfoLoadingAdapter:LoadStateAdapter<UserInfoLoadingAdapter.UserInfoLoadingViewHolder>() {

    override fun onBindViewHolder(holder: UserInfoLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = UserInfoLoadingViewHolder(parent)

    class UserInfoLoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_loading,parent,false)
    ){
        private val tvLoading:AppCompatTextView = itemView.findViewById(R.id.tv_loading_status)

        fun bind(loadState: LoadState){
            tvLoading.text = "加载中..."
        }

    }

}