package com.example.RadarPop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings->{
                Toast.makeText(this, "Est-ce que t'as VRAIMENT besoin des settings?", Toast.LENGTH_LONG).show();
                true
            }
            R.id.favourite->{

                Toast.makeText(this, "T'as raison, moi aussi je l'adore celui-là !", Toast.LENGTH_LONG).show();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}