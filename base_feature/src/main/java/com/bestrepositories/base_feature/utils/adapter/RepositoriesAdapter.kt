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

    private lateinit var viewHolder: RepositoriesViewHolder

    override val layoutId = R.layout.item_repository

    override fun createViewHolderInstance(view: View): RepositoriesViewHolder {
        viewHolder = RepositoriesViewHolder(view)
        return viewHolder
    }

    fun updateLike(like: Boolean) = viewHolder.updateLike(like)

    inner class RepositoriesViewHolder(private val view: View) :
        BaseViewHolder<RepositoryBinding>(view) {

        private lateinit var itemRepositoryAvatar: AppCompatImageView
        private lateinit var itemRepositoryRepoName: AppCompatTextView
        private lateinit var itemRepositoryUsername: AppCompatTextView
        private lateinit var itemRepositoryStars: AppCompatTextView
        private lateinit var itemRepositoryForks: AppCompatTextView
        private lateinit var itemRepositoryWatchers: AppCompatTextView
        private lateinit var itemRepositoryLike: AppCompatImageView
        private lateinit var itemRepositoryProgressBar: ProgressBar

        override fun bind(item: RepositoryBinding) {
            itemRepositoryAvatar = view.findViewById(R.id.itemRepositoryAvatarImageView)
            itemRepositoryRepoName = view.findViewById(R.id.itemRepositoryRepoNameTextView)
            itemRepositoryUsername = view.findViewById(R.id.itemRepositoryUsernameTextView)
            itemRepositoryStars = view.findViewById(R.id.itemRepositoryStarsTextView)
            itemRepositoryForks = view.findViewById(R.id.itemRepositoryForksTextView)
            itemRepositoryWatchers = view.findViewById(R.id.itemRepositoryWatchersTextView)
            itemRepositoryLike = view.findViewById(R.id.itemRepositoryLikeImageView)
            itemRepositoryProgressBar = view.findViewById(R.id.itemRepositoryProgressBar)

            itemRepositoryProgressBar.setVisible()
            itemRepositoryAvatar.loadUrl(url = item.owner.avatarUrl,
                onSuccess = { itemRepositoryProgressBar.setGone() },
                onFailure = { itemRepositoryProgressBar.setGone() })

            itemRepositoryRepoName.text = item.name
            itemRepositoryUsername.text = item.owner.login
            itemRepositoryStars.text =
                view.context.getString(R.string.repository_stars, item.stargazersCount)
            itemRepositoryForks.text =
                view.context.getString(R.string.repository_forks, item.forksCount)
            itemRepositoryWatchers.text =
                view.context.getString(R.string.repository_watchers, item.watchersCount)

            itemRepositoryLike.setOnClickListener {
                likeListener(item)
                updateLike(!item.like)
            }

            updateLike(item.like)

            itemView.setOnClickListener { clickListener(item) }
        }

        fun updateLike(like: Boolean) {
            when (like) {
                true -> itemRepositoryLike.setImageDrawable(
                    view.getDrawable(R.drawable.ic_heart)
                )
                false -> itemRepositoryLike.setImageDrawable(
                    view.getDrawable(R.drawable.ic_heart_outline)
                )
            }
        }
    }
}