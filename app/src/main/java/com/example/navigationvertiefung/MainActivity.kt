package com.example.navigationvertiefung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.navigationvertiefung.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewmodel: MainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavView.setupWithNavController(navController)

        binding.navFAB.setOnClickListener {

            logBackStack(navController)
        }

        //Listener der bei jeder Navigation ausgeführt
//        navController.addOnDestinationChangedListener{ navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
//
//            logBackStack(navController)
//            Log.d("navDestination", navDestination.label.toString())
//
//            when(navDestination.id){
//                //Entfernt alle Destinations vom Stack bis zum ersten Destination mit der angegebenen id
//                R.id.homeFragment -> navController.popBackStack(R.id.homeFragment, false)
//                R.id.listFragment -> navController.popBackStack(R.id.listFragment, false)
//            }
//        }

        //Listener der bei BottomNavigation Klick ausgeführt
        binding.bottomNavView.setOnItemSelectedListener { menuItem ->

            //"Standard Listener"
//            NavigationUI.onNavDestinationSelected(menuItem, navController)


            when(menuItem.itemId){
                R.id.homeFragment -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    navController.popBackStack(R.id.homeFragment, false)
                }
                R.id.listFragment -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    navController.popBackStack(R.id.listFragment, false)
                }
            }
            true
        }

    }


    fun logBackStack(navController: NavController){
        //Print navController Backstack
        val backStackList = navController.currentBackStack.value
        val backStackString = backStackList.map {
            it.destination.label
        }
        Log.d("Backstack", backStackString.toString())
    }
}