package com.example.navigationvertiefung

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavView.setupWithNavController(navController)

        binding.navFAB.setOnClickListener {
            logBackStack(navController)
        }

        //Listener der bei jeder Navigation ausgeführt
        navController.addOnDestinationChangedListener { navController: NavController, navDestination: NavDestination, bundle: Bundle? ->

            if (navController.currentDestination!!.id == R.id.detailFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else {
                binding.bottomNavView.visibility = View.VISIBLE
            }
        }

        //Listener der bei BottomNavigation Klick ausgeführt
        binding.bottomNavView.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                //Entfernt alle Destinations vom Stack bis zum ersten Destination mit der angegebenen id
                R.id.homeFragment -> {
                    navController.popBackStack(R.id.nav_graph, false)
                    navController.navigate(R.id.homeFragment)
                }

                R.id.listFragment -> {
                    navController.popBackStack(R.id.nav_graph, false)
                    navController.navigate(R.id.listFragment)
                }
            }
            true
        }


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.id == R.id.homeFragment ||
                    navController.currentDestination?.id == R.id.listFragment
                ) {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("Bitte Bestätigen")
                        .setPositiveButton("Ja") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("Nein") { _, _ -> }
                        .show()
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