package com.nikasov.weatherapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.data.local.model.DailyModel
import kotlinx.android.synthetic.main.item_daily.view.*

class DailyAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DailyModel>() {
        override fun areItemsTheSame(oldItem: DailyModel, newItem: DailyModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: DailyModel, newItem: DailyModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DailyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_daily,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DailyViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<DailyModel>) {
        differ.submitList(list)
    }

    class DailyViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DailyModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            val dateAndTypeTxt = "${item.date} • ${item.weather}"
            itemView.dateAndType.text = dateAndTypeTxt
            itemView.avgTemp.text = item.avgTemp
            itemView.icon.setImageDrawable(item.icon)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: DailyModel)
    }
}

