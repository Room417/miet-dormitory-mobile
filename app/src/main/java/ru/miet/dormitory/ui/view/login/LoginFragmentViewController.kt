package ru.miet.dormitory.ui.view.login

import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import ru.miet.dormitory.R
import ru.miet.dormitory.data.model.login.UserCredentials
import ru.miet.dormitory.databinding.FragmentLoginBinding
import ru.miet.dormitory.ui.stateholders.login.LoginViewModel

class LoginFragmentViewController(
    private val activity: FragmentActivity,
    binding: FragmentLoginBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: LoginViewModel,
    private val navController: NavController
) {
    private val username = binding.loginUsername
    private val password = binding.loginPassword
    private val signInButton = binding.loginSignInButton
    private val progressBar = binding.loginProgressBar

    fun setUpViews() {
        setUpLoginPostRequestObserver()
        setUpSignInButton()
    }

    private fun setUpLoginPostRequestObserver() {
        viewModel.loginPostRequestState.observe(lifecycleOwner) { response ->
            if (response.error != null) {
                Toast.makeText(activity, response.error, Toast.LENGTH_LONG).show()
            } else {
                progressBar.visibility = View.GONE
                navController.navigate(R.id.action_navigation_login_to_navigation_store)
            }
        }
    }

    private fun setUpSignInButton() {
        signInButton.setOnClickListener {
            if (isUsernameValid() && isPasswordValid()) {
                progressBar.visibility = View.VISIBLE
                viewModel.makeLoginPostRequest(
                    UserCredentials(
                        username.text.toString(),
                        password.text.toString()
                    )
                )
            } else {
                if (username.text.isNullOrEmpty())
                    username.error = activity.getText(R.string.login_empty_field_error)
                if (password.text.isNullOrEmpty())
                    password.error = activity.getText(R.string.login_empty_field_error)

            }
        }
    }

    private fun isUsernameValid(): Boolean = username.text?.isNotEmpty() == true

    private fun isPasswordValid(): Boolean = password.text?.isNotEmpty() == true
}
