package com.example.rickandmortyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmortyapp.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureNavigation()
    }

    private fun configureNavigation() {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.app_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.charactersFragment,
            R.id.locationsFragment,
            R.id.episodesFragment
        ))

        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.charactersFragment -> showBottomNav()
                R.id.locationsFragment -> showBottomNav()
                R.id.episodesFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private fun showBottomNav() {
        bottomNavigationView.visibility = View.VISIBLE
    }
    private fun hideBottomNav() {
        bottomNavigationView.visibility = View.GONE
    }
}