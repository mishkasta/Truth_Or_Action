package com.maxelfs.truthanddare.views.packs

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxelfs.truthanddare.views.ViewHolderBase

private const val PACK_AVAILABLE = 1
private const val PACK_UNAVAILABLE = 2
private const val PACK_HORIZONTAL = 3



@Suppress("UNCHECKED_CAST")
class PackAdapter constructor(
    private val _startGameClickListener: PackClickListener)
    : ListAdapter<PackViewModel, RecyclerView.ViewHolder>(PackDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PACK_AVAILABLE -> AvailablePackViewHolder.from(parent, _startGameClickListener)
            PACK_UNAVAILABLE -> UnavailablePackViewHolder.from(parent)
            PACK_HORIZONTAL -> HorizontalPackViewHolder.from(parent, _startGameClickListener)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val packItem = holder as ViewHolderBase<PackViewModel>
        val pack = getItem(position)
        packItem.bind(pack, itemCount)
    }

    override fun getItemViewType(position: Int): Int {
        val pack = getItem(position)

        return if(pack.isAvailable && pack.type == 1) PACK_AVAILABLE
        else if(pack.isAvailable && pack.type == 2) PACK_HORIZONTAL
        else PACK_UNAVAILABLE

    }
}