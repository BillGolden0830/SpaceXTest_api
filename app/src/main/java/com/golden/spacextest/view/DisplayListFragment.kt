package com.golden.spacextest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.golden.spacextest.databinding.LaunchViewBinding
import com.golden.spacextest.model.remote.LaunchesResponse
import com.golden.spacextest.viewmodel.SpacexViewModel

class DisplayListFragment: Fragment() {

    private val viewModel: SpacexViewModel by viewModels()

    private lateinit var binding: LaunchViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LaunchViewBinding.inflate(
            inflater,
            container,
            false
        )
        viewModel.launches.observe(viewLifecycleOwner){
            object: Observer<List<LaunchesResponse>> {
                override fun onChanged(t: List<LaunchesResponse>?) {
                    if (t != null) {
                        updateView(t)
                    }
                }

            }
        }
        return binding.root

    }

    private fun updateView(data: List<LaunchesResponse>) {
        binding.apply {
    }
}


}