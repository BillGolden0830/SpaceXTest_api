package com.golden.spacextest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.golden.spacextest.model.remote.SpacexNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Job()).launch {
            SpacexNetwork.spacexAPI.getCompanyInfo()
        }
    }
}