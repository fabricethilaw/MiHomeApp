package comfabricethilaw.smarthomeapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import comfabricethilaw.smarthomeapp.databinding.ActivityMainBinding
import comfabricethilaw.smarthomeapp.util.hide
import comfabricethilaw.smarthomeapp.util.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_statics, R.id.navigation_routines
            )
        )
        // setupActionBarWithNavController(navController, appBarConfiguration)
        Handler(Looper.getMainLooper()).post {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            if (controller.currentDestination?.id == R.id.navigation_add_room) {
                navView.hide()
            } else navView.show()
        }
        navView.setupWithNavController(navController)
    }
}
}