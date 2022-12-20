package ru.miet.dormitory.ui.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.miet.dormitory.App
import ru.miet.dormitory.databinding.FragmentProfileBinding
import ru.miet.dormitory.ioc.profile.ProfileFragmentComponent
import ru.miet.dormitory.ioc.profile.ProfileFragmentViewComponent
import ru.miet.dormitory.ui.stateholders.profile.ProfileViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: ProfileFragmentComponent
    private var fragmentViewComponent: ProfileFragmentViewComponent? = null

    private val viewModel: ProfileViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = ProfileFragmentComponent(
            applicationComponent,
            fragment = this,
            viewModel = viewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fragmentViewComponent = ProfileFragmentViewComponent(
            fragmentComponent,
            binding = binding,
            lifecycleOwner = viewLifecycleOwner
        ).apply {
            viewController.setUpViews()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentViewComponent = null
    }
}
