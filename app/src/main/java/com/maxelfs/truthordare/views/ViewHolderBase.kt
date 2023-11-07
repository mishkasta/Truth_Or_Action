package com.maxelfs.truthordare.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal abstract class ViewHolderBase<TItem> protected constructor(view: View)
    : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: TItem, itemsCount: Int)
}