package com.bestrepositories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bestrepositories.databinding.ActivityMainBinding
import com.bestrepositories.intent.utils.safeNavigateUp

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.mainNavHostFragment)
    }

    override fun onSupportNavigateUp() = navController.safeNavigateUp()
}