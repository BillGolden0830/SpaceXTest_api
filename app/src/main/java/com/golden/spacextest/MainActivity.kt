package com.golden.spacextest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.golden.spacextest.model.remote.LaunchesResponse
import com.golden.spacextest.model.remote.SpacexNetwork
import com.golden.spacextest.view.CompanyFragment
import com.golden.spacextest.view.DisplayListFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Job()).launch {
            SpacexNetwork.spacexAPI.getCompanyInfo()
            //traditional
            supportFragmentManager.beginTransaction()
                .replace(R.id.company_fragment_container, CompanyFragment())
                .commit()
            supportFragmentManager.beginTransaction()
                .replace(R.id.launch_fragment_container, DisplayListFragment())

            //ktx way

            /*supportFragmentManager.commit {
                replace(R.id.company_fragment_container, CompanyFragment())
                replace(R.id.launch_fragment_container, DisplayListFragment())
            }*/
        }
    }
}