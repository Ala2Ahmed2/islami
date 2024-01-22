package com.example.islami.fragments.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.ActivityMainBinding
import com.example.islami.fragments.ui.home.hadeth.HadethFragment
import com.example.islami.fragments.ui.home.quran.QuranFragment
import com.example.islami.fragments.ui.home.radio.RadioFragment
import com.example.islami.fragments.ui.home.tasbeh.TasbehFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        viewBinding.content
            .bottomNav
            .setOnItemSelectedListener { item ->
               val fragment :Fragment = when (item.itemId) {
                    R.id.navigation_quran -> {
                        QuranFragment()
                    }
                    R.id.navigation_radio -> {
                        RadioFragment()
                    }
                    R.id.navigation_hadeth -> {
                        HadethFragment()
                    }
                    R.id.navigation_sebha -> {
                        TasbehFragment()
                    }
                   else->{
                       QuranFragment()
                   }
                }
                pushFragment(fragment)
                true
            }
        viewBinding.content.bottomNav
            .selectedItemId = R.id.navigation_quran
    }
    private fun pushFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}