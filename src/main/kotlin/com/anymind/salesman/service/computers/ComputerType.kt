package com.anymind.salesman.service.computers

import com.anymind.generated.types.PaymentMethod


enum class ComputerType {
    DEFAULT;

    companion object {
        fun toComputerType(paymentMethod: PaymentMethod) = when {
            // payment method which doesn't come under default strategy should come here
            else -> DEFAULT
        }
    }

}