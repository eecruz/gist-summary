/** Assignment: Assignment 3
 *  @author: Emilio Cruz and Glenn Buyce
 *  @date: 4/4/23
 */

package edu.quinnipiac.ser210.gistsummary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import edu.quinnipiac.ser210.gistsummary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()

        // sets up navigation with menu components
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }

    // inflates toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // navigates to specified fragment from menu option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return super.onOptionsItemSelected(item) || item.onNavDestinationSelected(navController)

        /*
        val id = item.itemId
        val navController = findNavController(R.id.nav_host_fragment)
        return when (id) {
            R.id.shareFragment -> {
                val shareActionProvider: ShareActionProvider? =
                    MenuItemCompat.getActionProvider(item) as ShareActionProvider?
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, viewModel?.getSummary())
                if (shareActionProvider != null) {
                    shareActionProvider.setShareIntent(intent)
                    if (viewModel?.getSummary() != "@strings/generate") {
                        val shareActionProvider: ShareActionProvider? =
                            MenuItemCompat.getActionProvider(item) as ShareActionProvider?
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, viewModel?.getSummary())
                        if (shareActionProvider != null) {
                            shareActionProvider.setShareIntent(intent)
                        }
                    }
                    true
                }
                else

            }
            else -> {super.onOptionsItemSelected(item) || item.onNavDestinationSelected(navController)}
        }

         */
    }


}