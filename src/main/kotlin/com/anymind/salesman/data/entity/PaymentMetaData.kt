package com.anymind.salesman.data.entity

import java.io.Serializable

data class PaymentMetaData (
    val pointModifier: Double,
    val paymentModifier: Double
) : Serializable