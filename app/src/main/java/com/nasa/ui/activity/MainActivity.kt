package com.nasa.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.nasa.R
import com.nasa.databinding.ActivityMainBinding
import com.nasa.extension.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentNavController: LiveData<NavController>? = null

    var navController: NavController? = null
        private set


    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->

            // if you need to show/hide bottom nav or toolbar based on destination
            // binding.bottomNavigationView.isVisible = destination.id != R.id.itemDetail
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.hide()
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            setUpBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigationBar()
    }

    private fun setUpBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.main_navigation,
            R.navigation.menu_navigation
        )

        val controller = binding.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        controller.observe(this) { navController ->
            setupActionBarWithNavController(navController)

            // unregister old onDestinationChangedListener, if it exists
            currentNavController?.value?.removeOnDestinationChangedListener(
                onDestinationChangedListener
            )

            // add onDestinationChangedListener to the new NavController
            navController.addOnDestinationChangedListener(onDestinationChangedListener)
        }

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    fun bottomNavVisible(isVisible: Boolean) {
        if (::binding.isInitialized) {
            binding.navView.isVisible = isVisible
        }
    }
}