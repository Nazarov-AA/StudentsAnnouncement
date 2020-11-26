package com.tms.studentsannouncement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth.*

private const val SIGN_IN_CODE = 1

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SIGN_IN_CODE){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "${getInstance().currentUser?.displayName}", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "good-bye", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        if (getInstance().currentUser == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_CODE)
        }else {
            Toast.makeText(this, "${getInstance().currentUser?.displayName}", Toast.LENGTH_SHORT).show()
        }

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setOnNavigationItemSelectedListener {item->
            when(item.itemId) {
                R.id.navigation_home -> {

                    true
                }
                R.id.navigation_add->{
                    true
                }
                R.id.navigation_my_announcement->{
                    true
                }

                else -> false
            }
        }
    }
}