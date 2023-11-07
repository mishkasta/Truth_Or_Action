package com.maxelfs.truthordare.views.packs

class PackClickListener(private val clickListener: (pack: PackViewModel) -> Unit) {
    fun onClick(pack: PackViewModel) = clickListener(pack)
}