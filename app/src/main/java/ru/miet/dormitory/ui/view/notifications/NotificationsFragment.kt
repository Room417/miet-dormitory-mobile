package ru.miet.dormitory.ui.view.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.miet.dormitory.App
import ru.miet.dormitory.databinding.FragmentNotificationsBinding
import ru.miet.dormitory.ioc.notifications.NotificationsFragmentComponent
import ru.miet.dormitory.ioc.notifications.NotificationsFragmentViewComponent
import ru.miet.dormitory.ui.stateholders.notifications.NotificationsViewModel

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: NotificationsFragmentComponent
    private var fragmentViewComponent: NotificationsFragmentViewComponent? = null

    private val viewModel: NotificationsViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = NotificationsFragmentComponent(
            applicationComponent,
            fragment = this,
            viewModel = viewModel,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fragmentViewComponent = NotificationsFragmentViewComponent(
            fragmentComponent,
            binding,
            lifecycleOwner = viewLifecycleOwner,
        ).apply {
            viewController.setUpViews()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        fragmentViewComponent = null
    }
}
