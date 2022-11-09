package com.golden.spacextest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.golden.spacextest.R
import com.golden.spacextest.databinding.CompanyDisplayBinding
import com.golden.spacextest.model.remote.CompanyResponse
import com.golden.spacextest.viewmodel.SpacexViewModel

class CompanyFragment: Fragment() {

    private val viewModel: SpacexViewModel by viewModels()

    private lateinit var binding: CompanyDisplayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CompanyDisplayBinding.inflate(
            inflater,
            container,
            false
        )
        viewModel.company.observe(viewLifecycleOwner, object: Observer<CompanyResponse> {
            override fun onChanged(t: CompanyResponse?) {
                if (t != null) {
                    updateView(t)
                }
            }
        })
        return binding.root

        }

    private fun updateView(data: CompanyResponse) {

        binding.apply {
            tvBlurb.text = getString(
                R.string.blurb,
                data.name,
                data.founder,
                data.founded,
                data.employees,
                data.launch_sites,
                data.valuation
            )
        }
    }
}

