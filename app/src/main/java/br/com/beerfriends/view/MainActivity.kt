package br.com.beerfriends.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.beerfriends.R
import br.com.beerfriends.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    companion object {
        const val USER_EXTRA = "user"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_friend_list, R.id.nav_profile_edit, R.id.nav_add_friend
            ), drawerLayout
        )

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this,
            drawerLayout, binding.appBarMain.toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                bindingUser()
            }
        }
        drawerLayout.addDrawerListener(drawerToggle)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun bindingUser()  {
        val sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)
        val userName = sharedPreferences.getString("name", "Usuário não logado")
        val userEmail = sharedPreferences.getString("email", "Email não encontrado")

        val headerView = binding.navView.getHeaderView(0)

        var name: TextView = headerView.findViewById(R.id.name);
        name.text = userName;
        var email: TextView = headerView.findViewById(R.id.email);
        email.text = userEmail;
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        FirebaseAuth.getInstance().signOut()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = when (item.itemId) {
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return result
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}