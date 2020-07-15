package com.example.a11_fragments

import ArticleTag
import TabsActivity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class ChoiseDialogFragment(): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arrayChecked = booleanArrayOf(true,true,true,true,true,true)
        arguments?.let {
            arrayChecked[0] = it.getBoolean(ArticleTag.HELICOPTER.name)
            arrayChecked[1] = it.getBoolean(ArticleTag.JET.name)
            arrayChecked[2] = it.getBoolean(ArticleTag.PROPELLER.name)
        }

        val currentTags = arrayOf(true, true, true, true, true, true )
        val tags = arrayOf(
            ArticleTag.HELICOPTER.name,
            ArticleTag.JET.name,
            ArticleTag.PROPELLER.name
        )

        return AlertDialog.Builder(requireContext())
            .setTitle("Search aircraft")
            .setMultiChoiceItems(tags, arrayChecked) { dialog, which, isChecked ->
                arrayChecked[which] = isChecked
                for (i in arrayChecked.indices) {
                    val checked = arrayChecked[i]
                    if (checked) {
                        currentTags[i] = false
                    }
                }
            }
            .setPositiveButton("Применить") { _, _ -> showViewPagerFragment(arrayChecked.toTypedArray())}
             .setNegativeButton("Отмена") { _, _ ->  }
             .create()
    }

    fun showViewPagerFragment(tag: Array<Boolean>){
        parentFragment.let { it as TabsActivity }.onConfirm(tag)
    }
    companion object {
        fun newInstance(filter: Array<Boolean>): DialogFragment {

            val tmp = ChoiseDialogFragment()
            val args = Bundle()
            args.putBoolean(ArticleTag.HELICOPTER.name, filter[0])
            args.putBoolean(ArticleTag.JET.name, filter[1])
            args.putBoolean(ArticleTag.PROPELLER.name, filter[2])
            tmp.arguments = args

            return tmp

        }}
}