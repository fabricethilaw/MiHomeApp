package comfabricethilaw.smarthomeapp.ui.home

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import comfabricethilaw.smarthomeapp.R
import comfabricethilaw.smarthomeapp.databinding.FragmentDevicesBinding

class DevicesFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentDevicesBinding? = null
    private val binding get() = _binding!!

    private val destinationsLayouts = mutableListOf<ViewGroup>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentDevicesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val lightLayout =
            binding.container.findViewById<ViewGroup>(R.id.destination_layout_light)
        val thermostatLayout =
            binding.container.findViewById<ViewGroup>(R.id.destination_layout_thermostat)
        val fridgeLayout =
            binding.container.findViewById<ViewGroup>(R.id.destination_layout_fridge)
        val fansLayout =
            binding.container.findViewById<ViewGroup>(R.id.destination_layout_fans)
        val speakerLayout =
            binding.container.findViewById<ViewGroup>(R.id.destination_layout_speaker)

        destinationsLayouts.add(lightLayout)
        destinationsLayouts.add(thermostatLayout)
        destinationsLayouts.add(fridgeLayout)
        destinationsLayouts.add(fansLayout)
        destinationsLayouts.add(speakerLayout)
        binding.setupNavController()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun FragmentDevicesBinding.setupNavController() {
        devicesNavView.setOnNavigationItemSelectedListener { menuItem ->
            navigateToLayout(menuItem.itemId)
            true
        }

        devicesNavView.selectedItemId = R.id.navigation_light
    }

    private fun navigateToLayout(activeDestinationId: Int) {

        val target = when (activeDestinationId) {
            R.id.navigation_light -> R.id.destination_layout_light
            R.id.navigation_thermostat -> R.id.destination_layout_thermostat
            R.id.navigation_fridge -> R.id.destination_layout_fridge
            R.id.navigation_fans -> R.id.destination_layout_fans
            R.id.navigation_speaker -> R.id.destination_layout_speaker
            else -> R.id.destination_layout_light
        }
        TransitionManager.beginDelayedTransition(binding.root)
        destinationsLayouts.forEach { layout ->
            layout.isVisible = layout.id == target
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return DevicesFragment()
        }
    }
}