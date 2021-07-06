package comfabricethilaw.mihome.ui

import androidx.recyclerview.widget.DiffUtil

class RoomItem(
    val name: String,
    val icon: Int,
    val deviceCount: Int
)


val ROOM_DIFF_CALLBACK: DiffUtil.ItemCallback<RoomItem> = object : DiffUtil.ItemCallback<RoomItem>() {
    override fun areItemsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
        return oldItem.name == newItem.name && oldItem.icon == newItem.icon
    }
}