package cn.lancet.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import cn.lancet.paging.adapter.UserInfoAdapter
import cn.lancet.paging.adapter.UserInfoLoadingAdapter
import cn.lancet.paging.data.UserInfo
import cn.lancet.paging.databinding.ActivityMainBinding
import cn.lancet.paging.viewmodel.UserInfoViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mUserInfoPagingData: PagingData<UserInfo>
    private lateinit var mUserInfoViewModel: UserInfoViewModel
    private lateinit var mAdapter: UserInfoAdapter
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initData()

    }

    private fun initData() {

        mUserInfoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UserInfoViewModel::class.java]

        mAdapter = UserInfoAdapter()

        mAdapter.setOnItemClick(object : UserInfoAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                mAdapter.notifyItemChanged(position,"Elect")
            }
        })


        mBinding.rvUserInfo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter.withLoadStateFooter(UserInfoLoadingAdapter())
        }.addItemDecoration(
            MaterialDividerItemDecoration(
                this,
                MaterialDividerItemDecoration.VERTICAL
            )
        )

        lifecycleScope.launch {
            mUserInfoViewModel.getUserInfo().collectLatest {
                mUserInfoPagingData = it
                mAdapter.submitData(it)
            }
        }

    }

}