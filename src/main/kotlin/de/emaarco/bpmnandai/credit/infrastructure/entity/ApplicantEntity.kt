package de.emaarco.bpmnandai.credit.infrastructure.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ApplicantEntity(

    @Column(name = "first_name_", nullable = false)
    val firstName: String,

    @Column(name = "last_name_", nullable = false)
    val lastName: String,

    @Column(name = "email_", nullable = false)
    val email: String,

    @Column(name = "gender_", nullable = false)
    val gender: String,

    @Column(name = "age_", nullable = false)
    val age: Int,

    @Column(name = "family_status_", nullable = false)
    val familyStatus: String,

    @Column(name = "nr_of_children_", nullable = false)
    val nrOfChildren: String,

    @Column(name = "annual_income_", nullable = false)
    val annualIncome: Double,

    @Column(name = "job_", nullable = false)
    val job: String,

    @Column(name = "days_employed_", nullable = false)
    val daysEmployed: Int,

    @Column(name = "education_type_", nullable = false)
    val educationType: String,

    @Column(name = "housing_type_", nullable = false)
    val housingType: String,

    @Column(name = "owns_car_", nullable = false)
    val ownsCar: String,

    @Column(name = "owns_realty_", nullable = false)
    val ownsRealty: String,

    )