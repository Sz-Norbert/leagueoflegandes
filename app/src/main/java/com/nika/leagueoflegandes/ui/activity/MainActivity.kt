package com.nika.leagueoflegandes.ui.activity

import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nika.leagueoflegandes.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView : BottomNavigationView
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




         bottomNavigationView = findViewById(R.id.btm_menu)
         navController =  findNavController(R.id.fragmentContainerView2)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavVisibility()
    }


    fun bottomNavVisibility(){
        navController.addOnDestinationChangedListener{_ , destination, _->

            if (destination.id==R.id.startFragment){
                bottomNavigationView.visibility = View.GONE
            }else{
                bottomNavigationView.visibility=View.VISIBLE
            }
        }
    }
}