package com.example.navigationvertiefung

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
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

            //Rufe die Funktion auf die standardmäßig für die bottom navigation zuständig ist.
            NavigationUI.onNavDestinationSelected(menuItem, navController)
            navController.popBackStack(menuItem.itemId, false)

            true
        }


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {


                if (navController.currentDestination?.id == R.id.homeFragment ||
                    navController.currentDestination?.id == R.id.listFragment) {


                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("Bitte Bestätigen")

                    builder.setPositiveButton("Ja") { _, _ ->
                        finish()
                    }

                    builder.setNegativeButton("Nein") { _, _ ->

                    }

                    builder.show()
                } else {
                    navController.navigateUp()
                }

            }

        })

    }


    //Test Code, Warnungen können ignoriert werden da der Code vor Release entfernt wird
    fun logBackStack(navController: NavController) {
        //Print navController Backstack
        val backStackList = navController.currentBackStack.value
        val backStackString = backStackList.map {

            //it.destination.label
            //it.destination.displayName.split('/').last()
            it.destination.displayName.substringAfterLast('/')

        }
        Log.d("Backstack", backStackString.toString())
    }
}