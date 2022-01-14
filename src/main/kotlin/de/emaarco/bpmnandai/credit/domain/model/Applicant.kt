package de.emaarco.bpmnandai.credit.domain.model

data class Applicant(
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