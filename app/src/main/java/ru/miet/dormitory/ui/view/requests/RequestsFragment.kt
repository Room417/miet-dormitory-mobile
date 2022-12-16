package ru.miet.dormitory.ui.view.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.miet.dormitory.App
import ru.miet.dormitory.databinding.FragmentRequestsBinding
import ru.miet.dormitory.ioc.requests.RequestsFragmentComponent
import ru.miet.dormitory.ioc.requests.RequestsFragmentViewComponent
import ru.miet.dormitory.ui.stateholders.requests.RequestsViewModel

class RequestsFragment : Fragment() {
    private var _binding: FragmentRequestsBinding? = null
    private val binding get() = _binding!!

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: RequestsFragmentComponent
    private var fragmentViewComponent: RequestsFragmentViewComponent? = null

    private val viewModel: RequestsViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = RequestsFragmentComponent(
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
        _binding = FragmentRequestsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fragmentViewComponent = RequestsFragmentViewComponent(
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
