package com.example.datastoreexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.datastoreexample.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var userManager: UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userManager = UserManager(this)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString().toInt()

            GlobalScope.launch {
                userManager.storeUser(age,name)
            }

        }

        userManager.userAgeFlow.asLiveData().observe(this){
            binding.tvAge.text = it.toString()
        }

        userManager.userNameFlow.asLiveData().observe(this){
            binding.tvName.text = it
        }
    }
}