package com.skillbox.lists12

import android.os.Parcel
import android.os.Parcelable

sealed class Person: Parcelable {
    data class FamousPerson(
        val name: String,
        val avatarLink: String,
        val contextPerson: String,
        val typePerson: String,
    ): Person(), Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(avatarLink)
            parcel.writeString(contextPerson)
            parcel.writeString(typePerson)
        }

        override fun describeContents(): Int {
            TODO("Not yet implemented")
        }

        companion object CREATOR : Parcelable.Creator<FamousPerson> {
            override fun createFromParcel(parcel: Parcel): FamousPerson {
                return FamousPerson(parcel)
            }

            override fun newArray(size: Int): Array<FamousPerson?> {
                return arrayOfNulls(size)
            }
        }
    }


    data class FamousDeveloper(
        val name: String,
        val avatarLink: String,
        val typeDeveloper: String,
        val contextDeveloper: String

        ) : Person(), Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(avatarLink)
            parcel.writeString(typeDeveloper)
            parcel.writeString(contextDeveloper)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<FamousDeveloper> {
            override fun createFromParcel(parcel: Parcel): FamousDeveloper {
                return FamousDeveloper(parcel)
            }

            override fun newArray(size: Int): Array<FamousDeveloper?> {
                return arrayOfNulls(size)
            }
        }
    }
}

enum class PersonFigureEnum(val personFigureName: String) {
    PERSON("Известная личность"),
    DEVELOPER("Разработчик");
}