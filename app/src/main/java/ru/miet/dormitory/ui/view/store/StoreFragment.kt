package ru.miet.dormitory.ui.view.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.miet.dormitory.App
import ru.miet.dormitory.databinding.FragmentStoreBinding
import ru.miet.dormitory.ioc.store.StoreFragmentComponent
import ru.miet.dormitory.ioc.store.StoreFragmentViewComponent
import ru.miet.dormitory.ui.stateholders.store.StoreViewModel

class StoreFragment : Fragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: StoreFragmentComponent
    private var fragmentViewComponent: StoreFragmentViewComponent? = null

    private val viewModel: StoreViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = StoreFragmentComponent(
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
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fragmentViewComponent = StoreFragmentViewComponent(
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