package de.emaarco.bpmnandai.credit.api.transport

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Information to the applicant of a specific loan request")
data class ApplicantTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val age: Int,
    val familyStatus: String,
    val nrOfChildren: String,
    val annualIncome: Double,
    val job: String,
    val daysEmployed: Int,
    val educationType: String,
    val housingType: String,
    val ownsCar: String,
    val ownsRealty: String,
)