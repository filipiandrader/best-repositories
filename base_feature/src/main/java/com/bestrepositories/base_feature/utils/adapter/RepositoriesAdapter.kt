package com.bestrepositories.base_feature.utils.adapter

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bestrepositories.base_feature.R
import com.bestrepositories.base_feature.core.BaseAdapter
import com.bestrepositories.base_feature.core.BaseViewHolder
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.extensions.getDrawable
import com.bestrepositories.base_feature.utils.extensions.loadUrl
import com.bestrepositories.base_feature.utils.extensions.setGone
import com.bestrepositories.base_feature.utils.extensions.setVisible

class RepositoriesAdapter(
    val clickListener: (repository: RepositoryBinding) -> Unit,
    val likeListener: (repository: RepositoryBinding) -> Unit
) : BaseAdapter<RepositoryBinding, RepositoriesAdapter.RepositoriesViewHolder>() {

    var repositoryLiked: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override val layoutId = R.layout.item_repository

    override fun createViewHolderInstance(view: View) = RepositoriesViewHolder(view)

    inner class RepositoriesViewHolder(private val view: View) :
        BaseViewHolder<RepositoryBinding>(view) {

        private lateinit var itemRepositoryAvatar: AppCompatImageView
        private lateinit var itemRepositoryRepoName: AppCompatTextView
        private lateinit var itemRepositoryUsername: AppCompatTextView
        private lateinit var itemRepositoryLike: AppCompatImageView
        private lateinit var itemRepositoryProgressBar: ProgressBar

        override fun bind(item: RepositoryBinding) {
            itemRepositoryAvatar = view.findViewById(R.id.itemRepositoryAvatarImageView)
            itemRepositoryRepoName = view.findViewById(R.id.itemRepositoryRepoNameTextView)
            itemRepositoryUsername = view.findViewById(R.id.itemRepositoryUsernameTextView)
            itemRepositoryLike = view.findViewById(R.id.itemRepositoryLikeImageView)
            itemRepositoryProgressBar = view.findViewById(R.id.itemRepositoryProgressBar)

            itemRepositoryProgressBar.setVisible()
            itemRepositoryAvatar.loadUrl(url = item.owner.avatarUrl,
                onSuccess = { itemRepositoryProgressBar.setGone() },
                onFailure = { itemRepositoryProgressBar.setGone() })

            itemRepositoryRepoName.text = item.name
            itemRepositoryUsername.text = item.owner.login

            itemRepositoryLike.setOnClickListener { likeListener(item) }

            when (item.like) {
                true -> itemRepositoryLike.setImageDrawable(
                    view.getDrawable(R.drawable.ic_heart)
                )
                false -> itemRepositoryLike.setImageDrawable(
                    view.getDrawable(R.drawable.ic_heart_outline)
                )
            }

            itemView.setOnClickListener { clickListener(item) }
        }
    }
}