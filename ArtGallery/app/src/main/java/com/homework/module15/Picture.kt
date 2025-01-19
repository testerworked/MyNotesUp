package com.homework.module15


import android.os.Parcel
import android.os.Parcelable

data class Picture(
    val title: String,
    val author: String,
    val imageResId: Int
) : Parcelable {

    // Конструктор для создания объекта из Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "", // Чтение названия
        parcel.readString() ?: "", // Чтение автора
        parcel.readInt() // Чтение ресурса изображения
    )

    // Запись объекта в Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeInt(imageResId)
    }

    // Описание содержимого (обычно возвращает 0)
    override fun describeContents(): Int {
        return 0
    }

    // CREATOR для создания объектов из Parcel
    companion object CREATOR : Parcelable.Creator<Picture> {
        override fun createFromParcel(parcel: Parcel): Picture {
            return Picture(parcel)
        }

        override fun newArray(size: Int): Array<Picture?> {
            return arrayOfNulls(size)
        }
    }
}