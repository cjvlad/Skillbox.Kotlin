package com.example.a11_fragments

import ArticleTag
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class TagsFilterDialog : DialogFragment() {
    interface TagsFilterDialogListener {
        fun onApplyDialog(checkedTags: BooleanArray?)
        fun onRestoreInstanceState(savedInstanceState: Bundle)
    }

    lateinit var listener: TagsFilterDialogListener

    private val tags: Array<String> = ArticleTag.values().map { it.toString() }.toTypedArray()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val checkedTags = requireArguments().getBooleanArray(KEY_CHECKED_ITEMS)

        return AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.tags_filter_title))
            .setMultiChoiceItems(tags, checkedTags) { _, _, _ ->

            }
            .setPositiveButton(getString(R.string.dialog_apply)){ _, _ ->
                sendBackResult(checkedTags)
                dismiss()
            }
            .setNegativeButton(getString(R.string.dialog_cancel)) { _, _ ->
                dismiss()
            }
            .create()
    }

    private fun sendBackResult(checkedTags: BooleanArray?) {

        listener.onApplyDialog(checkedTags)
    }


    companion object {
        const val KEY_CHECKED_ITEMS = "checkedItemsKey"
    }
}