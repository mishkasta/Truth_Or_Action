package com.maxelfs.truthordare.services

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.interfaces.TooltipService
import com.tomergoldst.tooltips.ToolTip
import com.tomergoldst.tooltips.ToolTip.ALIGN_RIGHT
import com.tomergoldst.tooltips.ToolTipsManager
import javax.inject.Inject

class TooltipServiceImpl @Inject constructor() : TooltipService {
    private lateinit var _tooltipManager: ToolTipsManager
    private lateinit var _context: Context
    private lateinit var _root: ViewGroup


    override fun initialize(context: Context, root: ViewGroup) {
        _tooltipManager = ToolTipsManager()
        _context = context
        _root = root
    }

    override fun showTooltip(text: String, anchor: View, position: TooltipService.Position) {
        val builder = ToolTip.Builder(_context, anchor, _root, text, position.value)

        builder.setAlign(ALIGN_RIGHT)
        val backgroundColor = _context.getColor(R.color.white)
        builder.setBackgroundColor(backgroundColor)
        builder.setTextAppearance(R.style.TooltipTextAppearance)

        _tooltipManager.show(builder.build())
    }

    override fun hideTooltip() {
        _tooltipManager.dismissAll()
    }
}