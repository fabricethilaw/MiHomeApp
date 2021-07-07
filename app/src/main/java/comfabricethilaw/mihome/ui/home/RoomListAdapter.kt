package comfabricethilaw.mihome.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import comfabricethilaw.mihome.R
import comfabricethilaw.mihome.ui.ROOM_DIFF_CALLBACK
import comfabricethilaw.mihome.ui.RoomItem
import comfabricethilaw.mihome.ui.widget.TouchableCardView

class RoomListAdapter : ListAdapter<RoomItem, RoomViewHolder>(ROOM_DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val viewHolder: View = inflater.inflate(R.layout.list_item_room, parent, false)

        return RoomViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

}

class RoomViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {


    @SuppressLint("ClickableViewAccessibility")
    fun bindTo(item: RoomItem) {
        val titleView: TextView = itemView.findViewById(R.id.title)
        val subtitleView: TextView = itemView.findViewById(R.id.subtitle)
        val iconView: ImageView = itemView.findViewById(R.id.icon)
        val card: TouchableCardView = itemView.findViewById(R.id.card)
        card.provideParentView(itemView.findViewById(R.id.containerLayout))
        titleView.text = item.name
        val devicesNumbers = "x${item.deviceCount} ${itemView.context.getString(R.string.devices)}"
        subtitleView.text = devicesNumbers
        Glide.with(view).load(item.icon).into(iconView)
    }
}