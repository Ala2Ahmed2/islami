package com.example.islami.fragments.ui.home.tasbeh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentTasbehBinding

class TasbehFragment : Fragment() {

    lateinit var viewBinding: FragmentTasbehBinding
    private var counter = 0
    private val phrases = listOf("سبحان الله", "الحمد لله", "الله أكبر")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTasbehBinding.inflate(inflater, container, false)

        viewBinding.buttonCount.setOnClickListener {
            counter = (counter + 1) % (phrases.size * 33)
            updateTasbehText()
            rotateSebha()
        }
        return viewBinding.root
    }

    private fun updateTasbehText() {
        val index = (counter / 33) % phrases.size
        viewBinding.buttonCount.text = phrases[index]
        viewBinding.textCounter.text = ((counter % 33) + 1).toString()

        if (counter % 33 == 0) {
            if (counter == phrases.size * 33) {
                counter = 0
            }
        }
    }

    private fun rotateSebha() {
        viewBinding.bodySebhaLogo.animate().rotationBy(45f).setDuration(500).start()
    }
}