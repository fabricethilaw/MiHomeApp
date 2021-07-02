package comfabricethilaw.smarthomeapp

import comfabricethilaw.smarthomeapp.ui.RoomItem

val userRooms = mutableListOf<RoomItem>().apply {
    add(
        RoomItem(
            "Living Room", R.drawable.ic_room_living_room, 3
        )
    )
    add(
        RoomItem(
            "Kitchen", R.drawable.ic_room_kitchen, 3
        )
    )
    add(
        RoomItem(
            "Bathroom", R.drawable.ic_room_bathroom, 3
        )
    )

    add(
        RoomItem(
            "Bedroom", R.drawable.ic_room_bedroom, 3
        )
    )

    add(
        RoomItem(
            "Garage", R.drawable.ic_room_garage, 3
        )
    )

    add(
        RoomItem(
            "Office", R.drawable.ic_room_office, 3
        )
    )

    add(
        RoomItem(
            "Kids Room", R.drawable.ic_room_kids_room, 3
        )
    )

    add(
        RoomItem(
            "TV Room", R.drawable.ic_room_tv_room, 3
        )
    )

}