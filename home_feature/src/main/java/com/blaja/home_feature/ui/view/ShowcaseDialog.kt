package com.blaja.home_feature.ui.view

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.blaja.home_feature.R
import com.blaja.movies_data.model.MovieItem

class ShowcaseDialog(private val items: MutableList<String?>,private val listener: OnLanguageClickListener) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val alertBuilder = AlertDialog.Builder(it)

            alertBuilder.setTitle("Selecciona un idioma")
            alertBuilder.setItems(items.toTypedArray(), DialogInterface.OnClickListener{ _, index ->
                items[index]?.let { it -> listener.setOnLanguageClickListener(it) }
            })
            alertBuilder.create()
        } ?: throw IllegalStateException("Exception !! Activity is null !!")
    }

    interface OnLanguageClickListener {
        fun setOnLanguageClickListener(lang: String)
    }

    companion object{
        const val SHOWCASE_DIALOG_TAG = "showcaseDialog"
    }
}