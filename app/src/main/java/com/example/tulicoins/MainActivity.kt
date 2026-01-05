package com.example.tulicoins

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val firstFragment = FirstFragment()
    private val secondFragment = SecondFragment()
    private val thirdFragment = ThirdFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegation)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navigation.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.firstFragment -> {
                    loadFragment(firstFragment)
                    true
                }
                R.id.secondFragment -> {
                    loadFragment(secondFragment)
                    true
                }
                R.id.thirdFragment -> {
                    loadFragment(thirdFragment)
                    true
                }
                else -> false
            }
        }

        // load the first Fragment
        loadFragment(firstFragment)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}
