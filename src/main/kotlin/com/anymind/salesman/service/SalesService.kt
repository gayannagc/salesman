package com.anymind.salesman.service

import com.anymind.generated.types.Payment
import com.anymind.generated.types.PaymentRequest
import com.anymind.generated.types.SalesData
import com.anymind.generated.types.SalesDataRequest
import com.anymind.salesman.common.GenericException
import com.anymind.salesman.data.dto.ComputePaymentRequest
import com.anymind.salesman.data.dto.ComputePointsRequest
import com.anymind.salesman.data.dto.FinalPayment
import com.anymind.salesman.data.dto.Points
import com.anymind.salesman.data.entity.PaymentEntity
import com.anymind.salesman.data.entity.PaymentMetaData
import com.anymind.salesman.data.repository.PaymentRepository
import com.anymind.salesman.service.computers.ComputerType
import com.anymind.salesman.service.computers.PointComputer
import com.anymind.salesman.service.computers.PriceComputer
import mu.KotlinLogging
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

private val log = KotlinLogging.logger {}

@Service
class SalesService(
    private val priceComputerList: List<PriceComputer>,
    private val pointComputerList: List<PointComputer>,
    private val paymentRepository: PaymentRepository
) {
    private val priceComputerMap = mutableMapOf<ComputerType, PriceComputer>()
    private val pointComputerMap = mutableMapOf<ComputerType, PointComputer>()

    @PostConstruct
    fun init() {
        priceComputerList.forEach { priceComputerMap[it.computerType()] = it }
        pointComputerList.forEach { pointComputerMap[it.computerType()] = it }
    }

    fun processPayment(request: PaymentRequest): Payment {
        val computerType = ComputerType.toComputerType(request.paymentMethod)
        val finalPrice = priceComputerMap[computerType]?.compute(ComputePaymentRequest.convertFrom(request))
        val points = pointComputerMap[computerType]?.compute(ComputePointsRequest.convertFrom(request))
        val payment = createPayment(finalPrice, points, request)
        return Payment.newBuilder()
            .finalPrice(payment.finalPrice.toString())
            .points(payment.pointsAwarded)
            .build()
    }

    fun getSales(request: SalesDataRequest): SalesData {
        TODO("Not yet implemented")
    }

    private fun createPayment(
        finalPayment: FinalPayment?,
        points: Points?,
        request: PaymentRequest
    ): PaymentEntity {
        try {
            if (finalPayment != null && points != null) {
                return paymentRepository.save(
                    PaymentEntity(
                        price = request.price.toDouble(),
                        finalPrice = finalPayment.finalPrice,
                        pointsAwarded = points.awardedPoints,
                        paymentMethod = request.paymentMethod,
                        createdDate = request.datetime,
                        paymentMetaData = PaymentMetaData(
                            paymentModifier = request.priceModifier,
                            pointModifier = points.pointModifier
                        )
                    )
                )
            } else {
                log.error { "finalPayment ${finalPayment.toString()} or points ${points.toString()} is null" }
                throw GenericException("unexpected error occur while processing the payment")
            }
        } catch (e: Exception) {
            log.error { "error occurred while persisting the payment: ${e.message}" }
            throw GenericException("unexpected error occur while processing the payment")
        }
    }
}