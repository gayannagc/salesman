package com.anymind.salesman.data.dto

import com.anymind.generated.types.PaymentRequest
import com.anymind.salesman.service.computers.ComputerType
import java.math.BigDecimal

open class ComputePaymentRequest (
    val price: Double,
    val modifier: Double,
    val strategy : ComputerType = ComputerType.DEFAULT
) {
    companion object {
        fun convertFrom(request: PaymentRequest): ComputePaymentRequest {
            return ComputePaymentRequest(
                price = request.price.toDouble(),
                modifier = request.priceModifier
            )
        }
    }
}

class FinalPayment (
    val finalPrice : Double,
    val discount : Double
)


