package com.anymind.salesman.service.computers

import com.anymind.salesman.common.GenericException
import com.anymind.salesman.data.dto.ComputePaymentRequest
import com.anymind.salesman.data.dto.FinalPayment
import com.anymind.salesman.data.repository.PaymentMethodConfigRepository
import org.springframework.stereotype.Component
import java.math.BigDecimal

interface PriceComputer {
    fun computerType() : ComputerType
    fun compute(request: ComputePaymentRequest) : FinalPayment
}

@Component
class DefaultPriceComputer(
    val paymentMethodConfigRepository : PaymentMethodConfigRepository
): PriceComputer {

    override fun computerType(): ComputerType {
        return ComputerType.DEFAULT
    }

    override fun compute(request: ComputePaymentRequest): FinalPayment {
        try {
            return FinalPayment(
                finalPrice = request.price * request.modifier,
                discount = request.price * (1-request.modifier)
            )
        }catch (e: Exception){
            throw GenericException("exception while calculating price")
        }
    }

}

