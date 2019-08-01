package com.zues.islamic.ui.prayerTiming

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zues.islamic.R
import com.zues.islamic.models.Data

/* ---  Created by akhtarz on 7/15/2019. ---*/
class MonthTimingsAdapter(private val items: List<Data>) :
    RecyclerView.Adapter<MonthTimingsAdapter.MonthTimingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MonthTimingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.timing_item, parent, false)
        return MonthTimingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MonthTimingViewHolder, position: Int) {
        holder.date.text = items.get(position).date.readable
        holder.fajar.text = items.get(position).timings.Fajr
        holder.zuhur.text = items.get(position).timings.Dhuhr
        holder.asar.text = items.get(position).timings.Asr
        holder.magrib.text = items.get(position).timings.Maghrib
        holder.isha.text = items.get(position).timings.Isha
    }

    inner class MonthTimingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val fajar: TextView
        val zuhur: TextView
        val asar: TextView
        val magrib: TextView
        val isha: TextView
        val date: TextView

        init {
            date = view.findViewById(R.id.tv_date)
            fajar = view.findViewById(R.id.tv_fajar)
            zuhur = view.findViewById(R.id.tv_zuhur)
            asar = view.findViewById(R.id.tv_asar)
            magrib = view.findViewById(R.id.tv_magrib)
            isha = view.findViewById(R.id.tv_isha)
        }

    }
}