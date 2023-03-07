package com.anymind.salesman.service.computers


import com.anymind.salesman.common.GenericException
import com.anymind.salesman.data.dto.ComputePointsRequest
import com.anymind.salesman.data.dto.Points
import com.anymind.salesman.data.repository.PaymentMethodConfigRepository
import org.springframework.stereotype.Component

interface PointComputer {
    fun computerType() : ComputerType
    fun compute(request: ComputePointsRequest) : Points
}

@Component
class DefaultPointComputer(
    val paymentMethodConfigRepository : PaymentMethodConfigRepository
): PointComputer {

    override fun computerType(): ComputerType {
        return ComputerType.DEFAULT
    }

    override fun compute(request: ComputePointsRequest): Points {
        try {
            val paymentMethodConfig = paymentMethodConfigRepository.findByPaymentMethod(request.paymentMethod)
            return Points(
                awardedPoints = paymentMethodConfig.pointConfig.pointModifier * request.price.toInt(),
                pointModifier = paymentMethodConfig.pointConfig.pointModifier
            )
        }catch (e: Exception){
            throw GenericException("exception ${e.message} while calculating points")
        }
    }
}