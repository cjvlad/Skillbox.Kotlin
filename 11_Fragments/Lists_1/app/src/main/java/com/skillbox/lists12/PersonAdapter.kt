package com.skillbox.lists12

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PersonAdapter(
    private val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var persones: List<Person> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_PERSON -> PersonHodler(parent.inflate(R.layout.item_person, false), onItemClick)
            TYPE_DEVELOPER -> DeveloperHolder(parent.inflate(R.layout.item_developer, false), onItemClick)
            else -> error("Incorrect viewType=$viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(persones[position]){
            is  Person.FamousPerson -> TYPE_PERSON
            is  Person.FamousDeveloper -> TYPE_DEVELOPER
        }
    }

    override fun getItemCount(): Int {
        return persones.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PersonHodler -> {
                val personality = persones[position].let { it as? Person.FamousPerson }
                    ?: error("Movie Figure on position = $position is not actor")
                holder.bind(personality)
            }
            is DeveloperHolder -> {
                val personality = persones[position].let { it as? Person.FamousDeveloper }
                    ?: error("Movie Figure on position = $position is not film director")
                holder.bind(personality)
            }
            else -> error("Incorrect view holder = $holder")
        }
    }

    abstract class BaseMovieFiguresHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.nameTextView)
        val specializationTextView = view.findViewById<TextView>(R.id.specializationTextView)
        val informationTextView = view.findViewById<TextView>(R.id.informationTextView)
        val imageViewAvatar = view.findViewById<ImageView>(R.id.avatarImageView)

        init {
            view.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        protected fun bindMainInfo(
            name: String,
            specialization: String,
            info: String,
            avatarLink: String
        ) {
            textViewName.text = name
            specializationTextView.text = itemView.context.resources.getString(R.string.specializationPlaceHolder, specialization)
            informationTextView.text = itemView.context.resources.getString(R.string.infoPlaceHolder, info)

            Glide.with(itemView)
                .load(avatarLink)
                .placeholder(R.drawable.ic_portrait)
                .into(imageViewAvatar)
        }
    }

    class PersonHodler(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): BaseMovieFiguresHolder(view, onItemClick){
        val textViewGenres = view.findViewById<TextView>(R.id.typeTextView)

        fun bind(personality: Person.FamousPerson){
            bindMainInfo(personality.name, personality.contextPerson, personality.typePerson, personality.avatarLink)

            textViewGenres.text = itemView.context.resources.getString(R.string.typePlaceHolder, personality.subTypes)
        }
    }

    class DeveloperHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): BaseMovieFiguresHolder(view, onItemClick){
        fun bind(personality: Person.FamousDeveloper){
            bindMainInfo(personality.name, personality.typeDeveloper, personality.contextDeveloper, personality.avatarLink)
        }
    }

    fun updatePersons(newMovieFigure: List<Person>){
        persones = newMovieFigure
    }

    companion object{
        const val TYPE_PERSON = 1
        const val TYPE_DEVELOPER = 2
    }
}