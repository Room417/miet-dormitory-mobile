package ru.miet.dormitory.data.model.profile

import java.time.LocalDate

data class Resident(
    val id: Int,
    val fullName: String,
    val dateOfBirth: LocalDate,
    val placeOfBirth: String,
    val grade: String,
    val studyDirection: String,
    val group: String,
    val studentCard: String,
    val buildingNumber: Int,
    val roomNumber: Int,
    val enrollmentDate: LocalDate,
    val enrollmentOrderNumber: Int,
    val placeProvisionInDormitoryOrderNumber: Int,
    val profilePictureId: Int
)
