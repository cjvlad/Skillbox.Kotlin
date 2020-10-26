package com.skillbox.lists12

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_developer.view.*
import kotlinx.android.synthetic.main.add_person.view.*

class InfoDialogFragment : DialogFragment() {

    companion object {
        private const val FIGURE_NUMBER = "figure number"

        fun newInstance(figureNumber: Int): InfoDialogFragment {
            return InfoDialogFragment().withArguments {
                putInt(FIGURE_NUMBER, figureNumber)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val number = requireArguments().getInt(FIGURE_NUMBER)
        return when (PersonFigureEnum.values()[number]) {
            PersonFigureEnum.PERSON -> onCreatePersonDialog()
            PersonFigureEnum.DEVELOPER -> onCreateDeveloperDialog()
        }
    }

    private fun onCreatePersonDialog(): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.add_person, null)
        val dialog = AlertDialog.Builder(context)
            .setTitle("Add characteristic of a new movie figure:")
            .setView(view)
            .setPositiveButton("add") { _, _ ->
                var name = view.etNamePerson_dialogFragment.text.toString()
                var info = view.etContextPerson.text.toString()
                if (name == "") name = "Anonym"
                var link = view.etLinkPerson_dialogFragment.text.toString()
                val spinnerType = view.spinnerType.selectedItem.toString()
                val person = Person.FamousPerson(name, info, "", spinnerType, link )
                (parentFragment as DialogButtonClick).onPositiveButtonClick(person)
            }
            .setNegativeButton("Отмена") { _, _ -> }
            .create()

        view.etNamePerson_dialogFragment.addTextChangedListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                view.etNamePerson_dialogFragment.text.isNotBlank()
        }
        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
        }

        return dialog
    }

    private fun onCreateDeveloperDialog(): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.add_developer, null)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Добавить нового разработчика:")
            .setView(view)
            .setPositiveButton("Добавить") { _, _ ->
                var name = view.etNameDeveloper_dialogFragment.text.toString()
                var info = view.etContextDeveloper.text.toString()
                if (name == "") name = "Anonym"
                var link = view.etLinkDeveloper_dialogFragment.text.toString()
                val developer = Person.FamousDeveloper(name, info, "", link )
                (parentFragment as DialogButtonClick).onPositiveButtonClick(developer)
            }
            .setNegativeButton("Отмена") { _, _ -> }
            .create()

        view.etNameDeveloper_dialogFragment.addTextChangedListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                view.etNamePerson_dialogFragment.text.isNotBlank()
        }
        
        
        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
        }

        return dialog
    }
}