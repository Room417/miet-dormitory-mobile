package ru.miet.dormitory.ui.view.profile

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import ru.miet.dormitory.R
import ru.miet.dormitory.data.model.profile.ResidentGetRequestState
import ru.miet.dormitory.databinding.FragmentProfileBinding
import ru.miet.dormitory.ui.stateholders.profile.ProfileViewModel
import java.time.format.DateTimeFormatter

class ProfileFragmentViewController(
    private val activity: FragmentActivity,
    binding: FragmentProfileBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: ProfileViewModel,
    private val navController: NavController
) {
    private val swipeRefreshLayout = binding.profileSwipeRefreshLayout
    private val profilePicture = binding.profilePicture
    private val fullName = binding.profileFullName
    private val dateOfBirth = binding.profileDateOfBirth
    private val placeOfBirth = binding.profilePlaceOfBirth
    private val grade = binding.profileGrade
    private val studyDirection = binding.profileStudyDirection
    private val group = binding.profileGroup
    private val studentCard = binding.profileStudentCard
    private val building = binding.profileBuildingNumber
    private val room = binding.profileRoomNumber
    private val signOut = binding.profileSignOut

    fun setUpViews() {
        setUpResidentGetRequestObserver()
        setUpSwipeToRefresh()
        setUpSignOut()
    }

    private fun setUpResidentGetRequestObserver() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.residentGetRequestState.observe(lifecycleOwner) { response ->
            if (response.error != null) {
                Toast.makeText(activity, response.error, Toast.LENGTH_LONG).show()
            } else {
                setUpProfilePicture(response)
                setUpFullName(response)
                setUpDateOfBirth(response)
                setUpPlaceOfBirth(response)
                setUpGrade(response)
                setUpStudyDirection(response)
                setUpGroup(response)
                setUpStudentCard(response)
                setUpBuilding(response)
                setUpRoom(response)
            }
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpProfilePicture(response: ResidentGetRequestState) {
        response.responseBody?.resident?.profilePictureId?.let {
            profilePicture.setBackgroundResource(it)
        }
    }

    private fun setUpFullName(response: ResidentGetRequestState) {
        fullName.text = response.responseBody?.resident?.fullName
    }

    private fun setUpDateOfBirth(response: ResidentGetRequestState) {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        dateOfBirth.text = response.responseBody?.resident?.dateOfBirth?.format(dateTimeFormatter)
    }

    private fun setUpPlaceOfBirth(response: ResidentGetRequestState) {
        placeOfBirth.text = response.responseBody?.resident?.placeOfBirth
    }

    private fun setUpGrade(response: ResidentGetRequestState) {
        grade.text = response.responseBody?.resident?.grade
    }

    private fun setUpStudyDirection(response: ResidentGetRequestState) {
        studyDirection.text = response.responseBody?.resident?.studyDirection
    }

    private fun setUpGroup(response: ResidentGetRequestState) {
        group.text = response.responseBody?.resident?.group
    }

    private fun setUpStudentCard(response: ResidentGetRequestState) {
        studentCard.text = response.responseBody?.resident?.studentCard
    }

    private fun setUpBuilding(response: ResidentGetRequestState) {
        building.text = response.responseBody?.resident?.buildingNumber.toString()
    }

    private fun setUpRoom(response: ResidentGetRequestState) {
        room.text = response.responseBody?.resident?.roomNumber.toString()
    }

    private fun setUpSignOut() {
        signOut.setOnClickListener {
            viewModel.clearLoginPostRequestState()
            navController.navigate(R.id.action_navigation_profile_to_navigation_login)
        }
    }

    fun setUpSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.makeResidentGetRequest()
        }
    }
}
