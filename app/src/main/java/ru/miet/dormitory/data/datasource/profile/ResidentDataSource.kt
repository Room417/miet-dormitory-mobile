package ru.miet.dormitory.data.datasource.profile

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import ru.miet.dormitory.R
import ru.miet.dormitory.data.model.profile.Resident
import ru.miet.dormitory.data.model.profile.ResidentGetRequestResponseBody
import ru.miet.dormitory.data.model.profile.ResidentGetRequestState
import java.time.LocalDate

class ResidentDataSource {
    private val responseBody = ResidentGetRequestResponseBody(
        resident = Resident(
            id = 3,
            fullName = "Чернышев Афанасий Викторович",
            dateOfBirth = LocalDate.parse("2002-12-07"),
            placeOfBirth = "г. Балтийск",
            grade = "Бакалавр",
            studyDirection = "Программная инженерия",
            group = "ПИН-34",
            studentCard = "8200812",
            buildingNumber = 15,
            roomNumber = 528,
            enrollmentDate = LocalDate.parse("2020-08-30"),
            enrollmentOrderNumber = 92130192,
            placeProvisionInDormitoryOrderNumber = 1023703,
            profilePictureId = R.raw.profile_picture
        )
    )

    suspend fun makeResidentGetRequest(
        accessToken: String,
        userId: Int,
        residentGetRequestStateLiveData: MutableLiveData<ResidentGetRequestState>
    ) {
        delay(1000L)
        residentGetRequestStateLiveData.postValue(ResidentGetRequestState(responseBody))
    }
}
