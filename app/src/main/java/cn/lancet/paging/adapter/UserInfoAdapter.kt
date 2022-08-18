package cn.lancet.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.lancet.paging.R
import cn.lancet.paging.data.UserInfo
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.imageview.ShapeableImageView
import java.util.*

/**
 * Created by Lancet at 2022/8/18 18:42
 */
class UserInfoAdapter :
    PagingDataAdapter<UserInfo, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo) =
            oldItem.avatar == newItem.avatar
                    && oldItem.email == newItem.email && oldItem.firstName == newItem.firstName
                    && oldItem.lastName == newItem.lastName
    }) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = (Objects.requireNonNull<Any>(getItem(position)) as UserInfo)
        (holder as UserInfoViewHolder).tvName.text = "${data.firstName} ${data.lastName}"
        holder.tvEmail.text = data.email
        holder.ivAvatar.load(data.avatar){
            crossfade(true)
            placeholder(androidx.appcompat.R.drawable.abc_btn_borderless_material)
            transformations(CircleCropTransformation())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_user_item,parent,false))
    }


    internal class UserInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_avatar)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
    }


}