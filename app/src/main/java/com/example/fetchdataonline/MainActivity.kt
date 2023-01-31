package com.example.fetchdataonline

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchdataonline.viewmodel.SubscribersViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: SubscribersViewModel by lazy {
        ViewModelProvider(this).get(SubscribersViewModel::class.java)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        var progressBar = findViewById<ProgressBar>(R.id.progress)


        val adapter = SubscribersAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.properties.observe(this){
            subscribers -> subscribers.forEach { adapter.submitList(subscribers) }
            //progressBar.
            Log.i("First Subscribers", "${subscribers.get(1)}")
            //subscribers -> subscribers { adapter.submitList(it) }
        }

    }
}