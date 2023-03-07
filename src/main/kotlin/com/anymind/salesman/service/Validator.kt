package com.anymind.salesman.service

import com.anymind.generated.types.PaymentMethod
import com.anymind.generated.types.PaymentRequest

class Validator {

    companion object {
        fun validatePaymentCreateRequest(
            request: PaymentRequest
        ): Boolean {
            if (request.price.toDouble() >= 0){
                when(request.paymentMethod){
                    PaymentMethod.MASTERCARD -> (request.priceModifier in 0.95..1.0)
                    PaymentMethod.CASH -> (request.priceModifier in 0.9..1.0)
                    PaymentMethod.CASH_ON_DELIVERY -> (request.priceModifier in 1.0..1.2)
                    PaymentMethod.VISA -> (request.priceModifier in 0.95..1.0)
                    PaymentMethod.AMEX -> (request.priceModifier in 0.98..1.01)
                    PaymentMethod.JCB -> (request.priceModifier in 0.95..1.0)
                    else -> false
                }
            }
            return false
        }
    }
}