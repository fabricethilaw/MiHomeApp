package comfabricethilaw.mihome.ui.statics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import comfabricethilaw.mihome.R
import comfabricethilaw.mihome.databinding.FragmentHomeBinding
import comfabricethilaw.mihome.databinding.FragmentStaticsBinding


class StaticsFragment : Fragment() {

    private var _binding: FragmentStaticsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStaticsBinding.inflate(inflater, container, false)

        return _binding?.root
    }
    private fun setTopBarContent(binding: FragmentStaticsBinding) {
        binding.title.text = getString(R.string.statics)
        val userPicture = "https://randomuser.me/api/portraits/women/91.jpg"
        Glide.with(this).load(userPicture).circleCrop().into(binding.userPicture)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { setTopBarContent(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = StaticsFragment()
    }
}
