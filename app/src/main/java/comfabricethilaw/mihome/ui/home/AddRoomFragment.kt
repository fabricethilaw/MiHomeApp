package comfabricethilaw.mihome.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import comfabricethilaw.mihome.R
import comfabricethilaw.mihome.databinding.AddRoomFragmentBinding
import comfabricethilaw.mihome.ui.ROOM_DIFF_CALLBACK
import comfabricethilaw.mihome.ui.RoomItem
import comfabricethilaw.mihome.ui.widget.CheckableFrameLayout
import comfabricethilaw.mihome.userRooms

class AddRoomFragment : Fragment() {
    private var _binding: AddRoomFragmentBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            AddRoomFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val mAdapter = RoomSelectListAdapter(selectionCallback = {

        })

        binding?.list?.adapter = mAdapter
        populateRoomList(mAdapter, userRooms)

        binding?.inputSearch?.doAfterTextChanged {
            // todo filter room list
            if (it.toString().isNotBlank()) {
                userRooms.filter { room -> room.name.contains(it.toString(), true) }.run {
                    populateRoomList(mAdapter, this)
                }
            } else populateRoomList(mAdapter, userRooms)

            Toast.makeText(
                requireContext(), it.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    private fun populateRoomList(adapter: RoomSelectListAdapter, data: List<RoomItem>) {
        adapter.submitList(data)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

class RoomSelectListAdapter(private val selectionCallback: SelectionCallback) :
    ListAdapter<RoomItem, SelectViewHolder>(ROOM_DIFF_CALLBACK) {
    private val selectionMap = hashMapOf<String, Boolean>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val viewHolder = inflater.inflate(
            R.layout.list_item_select_room, parent, false
        )

        return SelectViewHolder(viewHolder, selectionMap, selectionCallback)
    }

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class SelectViewHolder(
    val view: View,
    val selectionMap: SelectionMap,
    val selectionCallback: SelectionCallback
) :
    RecyclerView.ViewHolder(view) {

    @SuppressLint("ClickableViewAccessibility")
    fun bind(item: RoomItem) {
        val container = view.findViewById<CheckableFrameLayout>(R.id.content)
        container.setOnCheckedChangeListener(null)
        container.setText(item.name)
        container.setIcon(item.icon)

        val state: Boolean = selectionMap[item.name] ?: false
        if (container.isChecked != state) {
            container.performClick()
        }
        Log.i("Check state", "${item.name} was checked ? $state")

        container.setOnCheckedChangeListener(object :
            CheckableFrameLayout.OnCheckedChangeListener {
            override fun onCheckedChanged(checkableView: View?, isChecked: Boolean) {
                selectionMap[item.name] = isChecked
                selectionCallback.invoke(selectionMap)
                Log.i("Check state", "${item.name} is now checked ? ${selectionMap[item.name]}")
                // todo
            }
        })
    }
}

private typealias SelectionCallback = (HashMap<String, Boolean>) -> Unit
private typealias SelectionMap = HashMap<String, Boolean>