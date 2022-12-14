package ru.miet.dormitory.ui.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.miet.dormitory.App
import ru.miet.dormitory.databinding.FragmentLoginBinding
import ru.miet.dormitory.ioc.login.LoginFragmentComponent
import ru.miet.dormitory.ioc.login.LoginFragmentViewComponent
import ru.miet.dormitory.ui.stateholders.login.LoginViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: LoginFragmentComponent
    private var fragmentViewComponent: LoginFragmentViewComponent? = null

    private val viewModel: LoginViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = LoginFragmentComponent(
            applicationComponent,
            fragment = this,
            viewModel = viewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fragmentViewComponent = LoginFragmentViewComponent(
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
