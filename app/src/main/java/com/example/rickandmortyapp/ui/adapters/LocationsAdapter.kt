package com.example.rickandmortyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.models.Location

class LocationsAdapter(
    callBack: DiffUtil.ItemCallback<Location>,
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<Location, LocationsAdapter.LocationViewHolder>(callBack){

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.locationsListItemNameTextView)
        private val type: TextView = itemView.findViewById(R.id.locationsListItemTypeTextView)
        private val dimension: TextView = itemView.findViewById(R.id.locationsListItemDimensionTextView)

        fun bind(location: Location?) {
            name.text = location?.name
            type.text = location?.type
            dimension.text = location?.dimension
        }

    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
        holder.itemView.setOnClickListener { onClick(checkNotNull(location).id) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }
}
object LocationComparator : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}