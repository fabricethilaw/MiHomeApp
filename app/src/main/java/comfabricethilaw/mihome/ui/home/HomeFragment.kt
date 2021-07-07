package comfabricethilaw.mihome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import comfabricethilaw.mihome.R
import comfabricethilaw.mihome.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setTopBarContent(binding: FragmentHomeBinding) {
        binding.title.text = "Your Home"
        binding.subtitle.text = "2715 Ash Dr. San Jose, South Dakota 83475"
        val userPicture = "https://randomuser.me/api/portraits/women/91.jpg"
        Glide.with(this).load(userPicture).circleCrop().into(binding.userPicture)
    }

    private fun setAddButton(button: FloatingActionButton) {
        button.setOnClickListener {
            showSheetForAddingDeviceOrRoom()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTopBarContent(binding)
        setAddButton(binding.addRoomOrDevice)
        binding.setupPager()
    }

    private fun FragmentHomeBinding.setupPager() {
        pager.doOnLayout {
            pager.adapter = HomePagerAdapter(childFragmentManager, lifecycle)

            pager.isUserInputEnabled = false // disable swipe

            TabLayoutMediator(tabs, pager) { tab, position ->
                tab.text = if (position == 0) getString(R.string.rooms) else getString(R.string.devices)
            }.attach()
        }
    }

    private fun showSheetForAddingDeviceOrRoom() {
        ModalAddDeviceOrRoom().show(parentFragmentManager, "modal")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}