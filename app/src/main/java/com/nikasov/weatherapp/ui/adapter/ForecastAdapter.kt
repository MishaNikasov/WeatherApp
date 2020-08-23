package com.nikasov.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.data.local.model.ForecastModel
import kotlinx.android.synthetic.main.item_forecast_long.view.*


class ForecastAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItem = 0

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ForecastModel>() {
        override fun areItemsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_forecast_long,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastViewHolder -> {

                holder.bind(differ.currentList[position])

                if (selectedItem == position) {
                    holder.itemView.background =
                        ContextCompat.getDrawable(holder.itemView.context, R.drawable.background_forecast)
                } else {
                    holder.itemView.background =
                        ContextCompat.getDrawable(holder.itemView.context, R.color.transparent)
                }

                holder.itemView.setOnClickListener {
                    val previousItem: Int = selectedItem
                    selectedItem = position
                    interaction?.onItemSelected(position, differ.currentList[position])

                    notifyItemChanged(previousItem)
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ForecastModel>) {
        differ.submitList(list)
    }

    class ForecastViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ForecastModel) = with(itemView) {
            itemView.day.text = item.day
            itemView.date.text = item.date
            itemView.weather.text = item.weather
            itemView.tempMax.text = item.tempMax
            itemView.tempMin.text = item.tempMin
            itemView.icon.setImageDrawable(item.icon)
        }

    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ForecastModel)
    }
}

