package comfabricethilaw.mihome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getDrawable
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import comfabricethilaw.mihome.R

class ModalAddDeviceOrRoom : BottomSheetDialogFragment(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customBackgroundDrawable =
            getDrawable(requireContext(), R.drawable.bg_rounded_bottomsheet)
        dialog?.window?.setBackgroundDrawable(null)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_device_or_room_modal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddNewRoom = view.findViewById<CardView>(R.id.btn_add_new_room)
        val btnAddNewDevice = view.findViewById<CardView>(R.id.btn_add_new_device)

        btnAddNewRoom.setOnClickListener(this)
        btnAddNewDevice.setOnClickListener(this)
        getDrawable(requireContext(), R.drawable.bg_rounded_bottomsheet)
        dialog?.window?.setBackgroundDrawable(null)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_add_new_room -> {
                findNavController().navigate(R.id.navigation_add_room)
            }
            R.id.btn_add_new_device -> {
                findNavController().navigate(R.id.navigation_add_device)
            }
        }
        dismiss()
    }
}