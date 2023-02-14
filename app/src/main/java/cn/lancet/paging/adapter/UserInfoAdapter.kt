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
import java.util.Objects

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

    private var mListener:OnItemClickListener?=null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            mListener?.onItemClick(position)
        }

        val data = (Objects.requireNonNull<Any>(getItem(position)) as UserInfo)
        (holder as UserInfoViewHolder).tvName.text = "${data.firstName} ${data.lastName}"
        holder.tvEmail.text = data.email
        holder.ivAvatar.load(data.avatar){
            crossfade(true)
            transformations(CircleCropTransformation())
        }

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        val holder = holder as UserInfoViewHolder

        val avatarUrl = "https://images.pexels.com/photos/2174974/pexels-photo-2174974.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"

        payloads.forEach {
            if (!it.toString().isNullOrBlank()){
                when{
                    it.toString().contains("E") -> {
                        holder.ivAvatar.load(avatarUrl)
                    }
                }


            }
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

    fun setOnItemClick(listener: OnItemClickListener){
        mListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position:Int) {}
    }

}