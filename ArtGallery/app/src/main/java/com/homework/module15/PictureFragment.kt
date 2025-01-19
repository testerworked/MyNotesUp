package com.homework.module15

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class PictureFragment : Fragment() {

    private var picture: Picture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Получаем данные о картине из аргументов
        arguments?.let {
            picture = it.getParcelable("picture") // Теперь это работает, так как Picture - Parcelable
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_picture, container, false)

        // Находим элементы интерфейса
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val ivPicture = view.findViewById<ImageView>(R.id.ivPicture)
        val tvAuthor = view.findViewById<TextView>(R.id.tvAuthor)

        // Устанавливаем данные о картине
        picture?.let {
            tvTitle.text = it.title
            ivPicture.setImageResource(it.imageResId)
            tvAuthor.text = it.author
        }

        return view
    }

    companion object {
        fun newInstance(picture: Picture): PictureFragment {
            val fragment = PictureFragment()
            val args = Bundle()
            args.putParcelable("picture", picture) // Передаем объект Picture через Bundle
            fragment.arguments = args
            return fragment
        }
    }
}