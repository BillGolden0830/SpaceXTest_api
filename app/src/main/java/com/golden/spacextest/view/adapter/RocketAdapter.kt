package com.golden.spacextest.view.adapter

import android.os.Build
import android.service.autofill.Dataset
import androidx.recyclerview.widget.RecyclerView
import com.golden.spacextest.databinding.LaunchViewBinding
import com.golden.spacextest.model.remote.LaunchRocket
import android.view.*
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class RocketAdapter(private val dataset: List<LaunchRocket>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        class RocketViewHolder(private val binding: LaunchViewBinding):
            RecyclerView.ViewHolder(binding.root) {
                @RequiresApi(Build.VERSION_CODES.O)
                fun onBind(dataItem: LaunchRocket){
                    binding.apply {
                        tvMission.text = dataItem.launchItem.name
                        val sdf = SimpleDateFormat("MM/DD/yyyy' @ 'HH:mm")
                            .format(dataItem.launchItem.date_utc)
                        tvDate.text = sdf
                        tvRocket.text = dataItem.launchItem.rocketID
                        val today = LocalDate.now()
                        val launch_day = LocalDate.parse(dataItem.launchItem.date_utc, DateTimeFormatter.ISO_DATE)
                        tvDaysSince.text = Period.between(today, launch_day).toString()

                    }
                }

        }

        class NoInternetRocketViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
        return if(dataset.isEmpty()) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType){
            1 -> {RocketViewHolder(LaunchViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
            }
            0 -> {NoInternetRocketViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    android.R.layout.simple_list_item_1,
                    parent,
                    false
                )
            )}
            else -> throw Exception("Invalid view")
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RocketViewHolder)
        when(holder){
            is RocketViewHolder -> {holder.onBind(dataset[position])}
            is NoInternetRocketViewHolder -> {}
        }
    }

    override fun getItemCount() = dataset.size
}