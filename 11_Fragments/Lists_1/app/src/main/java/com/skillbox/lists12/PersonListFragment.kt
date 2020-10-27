package com.skillbox.lists12

import android.app.AlertDialog
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_user_list.*

class PersonListFragment : Fragment(R.layout.fragment_user_list), DialogButtonClick {
    private var personFigures = arrayListOf<Person>()
    private var personFiguresAdapter by autoCleared<PersonAdapter>()
    private var dialog: AlertDialog? = null
    private var isDialog = false

    companion object {
        private const val KEY_FIGURES_LIST = "key_movie_figures_list"
        private const val IS_DIALOG = "is_dialog"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            personFigures = savedInstanceState.getParcelableArrayList(KEY_FIGURES_LIST)!!
            isDialog = savedInstanceState.getBoolean(IS_DIALOG)
        } else {
            personFigures = arrayListOf(
                Person.FamousDeveloper(
                    name = "Линус Торвальдс",
                    typeDeveloper = resources.getString(R.string.developerType),
                    avatarLink = "https://techrocks.ru/wp-content/uploads/2018/07/640px-Linus_Torvalds_2002_Australian_Linux_conference.jpg",
                    contextDeveloper = "Создатель операционной системы Linux"
                ),
                Person.FamousDeveloper(
                    name = "Дональд Кнут",
                    typeDeveloper = resources.getString(R.string.developerType),
                    avatarLink = "https://techrocks.ru/wp-content/uploads/2018/07/640px-Donald_Knuth_CHM_2011.jpg",
                    contextDeveloper = "Создатель системы компьютерной верстки TeX и языка METAFONT"
                ),
                Person.FamousDeveloper(
                    name = "Сэр Тим Бернерс-Ли",
                    typeDeveloper = resources.getString(R.string.developerType),
                    avatarLink = "https://techrocks.ru/wp-content/uploads/2018/07/Sir_Tim_Berners-Lee.jpg",
                    contextDeveloper = "Автор HTTP протокола"
                ),
                Person.FamousDeveloper(
                    name = "Джеймс Гослинг",
                    typeDeveloper = resources.getString(R.string.developerType),
                    avatarLink = "https://techrocks.ru/wp-content/uploads/2018/07/894px-James_Gosling_2008-640x644.jpg",
                    contextDeveloper = "Автор объектно-ориентированного языка Java"
                ),
                Person.FamousPerson(
                    name = "Леонардо да Винчи",
                    typePerson = resources.getString(R.string.painterType),
                    avatarLink = "https://avatars.mds.yandex.net/get-zen_doc/118284/pub_5a588055f4a0dd3e99c620aa_5a5886b98c8be3e20dc47ed2/scale_1200",
                    contextPerson = "Итальянский художник (живописец, скульптор, архитектор) и учёный (анатом, естествоиспытатель), изобретатель, писатель, музыкант, один из крупнейших представителей искусства Высокого Возрождения, яркий пример «универсального человека»",
                    subTypes = resources.getString(R.string.painterSubType)

                ),
                Person.FamousPerson(
                    name = "Винсент Ван Гог",
                    avatarLink = "https://avatars.mds.yandex.net/get-zen_doc/44972/pub_5a588055f4a0dd3e99c620aa_5a588786a815f16268273be7/scale_1200",
                    typePerson= resources.getString(R.string.painterType),
                    contextPerson = "Нидерландский художник-постимпрессионист, чьи работы оказали вневременное влияние на живопись XX века",
                    subTypes = resources.getString(R.string.painterSubType),
                ),
                Person.FamousPerson(
                    name = "Никола Тесла",
                    avatarLink = "https://cdn.fishki.net/upload/post/201603/07/1875568/ilklerin-adami-nikola-tesla_8667515-6012_1280x720.jpg",
                    typePerson = resources.getString(R.string.inventorType),
                    subTypes = resources.getString(R.string.inventorSubType),
                    contextPerson = "Выдающийся изобретатель, физик, инженер сербского происхождения, автор свыше сотни изобретений, многие из которых кардинально изменили жизнь человечества. Наибольшую известность получил за создание устройств функционирующих на переменном токе, а также последовательное отстаивание идеи существования эфира. Имя изобретателя носит единица измерения плотности магнитной индукции."
                ),
                Person.FamousPerson(
                    name = "Томас Эдисон",
                    avatarLink = "https://cdn.fishki.net/upload/post/201603/07/1875568/tn/tomas-jedisson-izuchal-diety-i-ih-vlijanie-na-cheloveka.jpg",
                    typePerson = resources.getString(R.string.inventorType),
                    subTypes = resources.getString(R.string.inventorSubType),
                    contextPerson = "Американский изобретатель и предприниматель, получивший в США 1093 патента[6] и около 3 тысяч в других странах мира[7]; создатель фонографа; усовершенствовал телеграф, телефон, киноаппаратуру, разработал один из первых коммерчески успешных вариантов электрической лампы накаливания[8], которая была доработкой других вариантов."
                ),
                Person.FamousPerson(
                    name = "Луи Армстронг",
                    avatarLink = "https://wallpaperaccess.com/full/2135237.jpg",
                    typePerson = resources.getString(R.string.musicianType),
                    subTypes = resources.getString(R.string.musicianSubType),
                    contextPerson = "Американский джазовый трубач, вокалист и руководитель ансамбля. Оказал (наряду с Дюком Эллингтоном, Чарли Паркером, Майлсом Дейвисом и Джоном Колтрейном) наибольшее влияние на развитие джаза и внёс значительный вклад в его популяризацию во всём мире"
                )
            )
        }
        init()
        fab_listFragment.setOnClickListener {
            selectTypeOfPerson()
        }
        updateAdapter()
        personFiguresAdapter.notifyDataSetChanged()
        if (isDialog) selectTypeOfPerson()

    }

    private fun init() {
        personFiguresAdapter = PersonAdapter { position: Int -> deletePerson(position) }
        with(itemView_listFragment) {
            adapter = personFiguresAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun selectTypeOfPerson() {
        isDialog = true
        val listOfFigures = PersonFigureEnum.values().map { it.personFigureName }.toTypedArray()
        dialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose a profession of figure")
            .setNegativeButton("Cancel") { _, _ -> }
            .setItems(listOfFigures) { _, which ->
                val movieFigureDialogFragment = InfoDialogFragment.newInstance(which)
                movieFigureDialogFragment.show(childFragmentManager, listOfFigures[which])
                isDialog = false
            }
            .setOnCancelListener { isDialog = false }
            .show()

    }

    private fun addPerson(movieFigure: Person) {
        personFigures.add(0, movieFigure)
        updateAdapter()
        personFiguresAdapter.notifyItemInserted(0)
        itemView_listFragment.scrollToPosition(0)
    }

    private fun deletePerson(position: Int) {
        if (position !in 0..personFigures.size) return
        personFigures.removeAt(position)
        updateAdapter()
        personFiguresAdapter.notifyItemRemoved(position)
    }

    private fun updateAdapter() {
        tv_movieFiguresListFragment.isVisible = personFigures.isEmpty()
        personFiguresAdapter.updatePersons(personFigures)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_FIGURES_LIST, personFigures)
        outState.putBoolean(IS_DIALOG, isDialog)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    override fun onPositiveButtonClick(persones: Person) {
        addPerson(persones)
    }
}