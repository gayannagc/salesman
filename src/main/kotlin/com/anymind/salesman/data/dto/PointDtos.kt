package com.anymind.salesman.data.dto

import com.anymind.generated.types.PaymentMethod
import com.anymind.generated.types.PaymentRequest

open class ComputePointsRequest (
    val price: Double,
    val paymentMethod: PaymentMethod
){
    companion object {
        fun convertFrom(request: PaymentRequest): ComputePointsRequest {
            return ComputePointsRequest(
                price = request.price.toDouble(),
                paymentMethod = request.paymentMethod
            )
        }
    }
}

class Points (
    val awardedPoints : Double,
    val pointModifier: Double
)