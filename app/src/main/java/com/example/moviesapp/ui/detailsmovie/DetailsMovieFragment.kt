package com.example.moviesapp.ui.detailsmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.core.movie.Movie
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.android.synthetic.main.fragment_details_movie.*
import com.google.mlkit.nl.translate.Translator

class DetailsMovieFragment : Fragment() {
    private lateinit var dashboardViewModel: DetailsMovieViewModel
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_details_movie, container, false)
        dashboardViewModel =
            ViewModelProviders.of(this).get(DetailsMovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details_movie, container, false)
        val textView: TextView = root.findViewById(R.id.text_detailMovie)

        movie = try {
            requireArguments().getSerializable("movie") as Movie
        } catch (i:IllegalStateException){
            Movie()
        }

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(movie.id == 0)
            mostrarVacio()
        else
            llenarDatos()
    }

    private fun mostrarVacio(){
        fullConstraint.visibility = View.GONE
        emptyConstraint.visibility = View.VISIBLE
    }

    private fun llenarDatos(){
        fullConstraint.visibility = View.VISIBLE
        emptyConstraint.visibility = View.GONE

        movie.title?.let {
            lblTitleCentral.text = "\"$it\""
        }
        movie.release_date?.let {
            lblDate.text = "($it)"
        }
        movie.original_title?.let {
            lblTitleO.text = it
        }
        movie.title?.let {
            lblTitleT.text = it
        }
        movie.original_language?.let {
            lblLenguaje.text = it
        }
        movie.adult.let {
            lblApto.text = if(it) "NO" else "SI"
        }

        movie.vote_count?.let {
            lblCantVotos.text = it
        }
        movie.vote_average?.let {
            lblPuntaje.text = "$it/10"
        }
        movie.overview?.let {
            lblOverwiev.text = it
        }

        movie.poster_path?.let {
            if(it.substring(it.length-4) != "null")
                Glide.with(requireContext())
                    .load(it)
                    .error(R.drawable.camara)
                    .into(imageView)
        }

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        val englishSpanishTranslator: Translator = Translation.getClient(options)
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        englishSpanishTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                englishSpanishTranslator.translate(movie.overview!!)
                    .addOnSuccessListener { translatedText ->
                        lblOverwiev2.text = translatedText
                    }
                    .addOnFailureListener { exception ->
                        lblOverwiev2.text = getString(R.string.no_traduce)
                    }
            }
            .addOnFailureListener { exception ->
                lblOverwiev2.text = getString(R.string.error_al_traducir)
            }

    }

    }