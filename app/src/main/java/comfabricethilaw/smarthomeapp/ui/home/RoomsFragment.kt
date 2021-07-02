package comfabricethilaw.smarthomeapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import comfabricethilaw.smarthomeapp.databinding.FragmentRoomsBinding
import comfabricethilaw.smarthomeapp.userRooms

class RoomsFragment : Fragment() {

    private lateinit var binding: FragmentRoomsBinding
    private val listAdapter = RoomListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomsBinding.inflate(inflater, container, false)
        binding.list.adapter = listAdapter
        populateRoomList(listAdapter)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun populateRoomList(adapter: RoomListAdapter) {
        adapter.submitList(userRooms)
    }


    companion object {
        @JvmStatic
        fun newInstance() = RoomsFragment()
    }
}

