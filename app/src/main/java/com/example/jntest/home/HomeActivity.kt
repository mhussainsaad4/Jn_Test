package com.example.jntest.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.jntest.R
import com.example.jntest.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initCode()
    }

    private fun initCode() {
        binding.lifecycleOwner = this@HomeActivity
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.navHostFragment.id
        ) as NavHostFragment
        navController = navHostFragment.navController
    }
}