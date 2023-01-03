package com.berlin.democlock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.berlin.democlock.BerlinClock.Companion.FIVE
import com.berlin.democlock.BerlinClock.Companion.TWO
import com.berlin.democlock.BerlinClockActivity.Companion.SPAN_COUNT_ELEVEN
import com.berlin.democlock.R
import com.berlin.democlock.databinding.BerlinClockItemLayoutBinding
import com.berlin.democlock.utils.LampColor
import com.berlin.democlock.utils.LampColor.*

class FiveMinutesAdapter :
    RecyclerView.Adapter<FiveMinutesAdapter.MyViewHolder>() {

    private lateinit var minutesOnTopLamps: List<LampColor>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView =
            BerlinClockItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(minutesOnTopLamps[position])
        if (shouldUpdateLightColor(position)) {
            holder.itemView.apply {
                holder.binding.itemBtn.background =
                    ContextCompat.getDrawable(context, R.drawable.minutes_button_background)
            }
        }
    }

    private fun shouldUpdateLightColor(position: Int) =
        minutesOnTopLamps.isNotEmpty() && (position == TWO ||
                position == FIVE || position == EIGHT)

    override fun getItemCount() = SPAN_COUNT_ELEVEN

    fun setMinutesValue(minutesOnTopValue: List<LampColor>) {
        minutesOnTopLamps = minutesOnTopValue
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: BerlinClockItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(minute: LampColor) {
            if (minute != RED)
                binding.itemBtn.isEnabled = minute != OFF
            else binding.itemBtn.isEnabled = minute == RED
        }
    }

    companion object {
        const val EIGHT = 8
    }
}