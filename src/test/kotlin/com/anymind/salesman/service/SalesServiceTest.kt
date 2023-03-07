package com.anymind.salesman.service

import com.anymind.generated.types.PaymentMethod
import com.anymind.generated.types.SalesDataRequest
import com.anymind.salesman.data.entity.PaymentEntity
import com.anymind.salesman.data.entity.PaymentMetaData
import com.anymind.salesman.data.repository.PaymentRepository
import com.anymind.salesman.service.computers.PointComputer
import com.anymind.salesman.service.computers.PriceComputer
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
internal class SalesServiceTest {

    @MockK
    lateinit var priceComputerList: List<PriceComputer>
    @MockK
    lateinit var pointComputerList: List<PointComputer>
    @MockK
    lateinit var paymentRepository: PaymentRepository
    @InjectMockKs
    lateinit var salesService: SalesService
    var paymentEntityList = arrayListOf<PaymentEntity>()

    @BeforeEach
    fun setUp() {
        paymentEntityList = arrayListOf(
            PaymentEntity(finalPrice = 10.0,pointsAwarded = 3.0,createdDate = LocalDateTime.parse("2023-03-07T13:05:30.00"), price = 0.0 ,paymentMethod = PaymentMethod.MASTERCARD, paymentMetaData = PaymentMetaData(paymentModifier = 0.0, pointModifier = 0.0)),
            PaymentEntity(finalPrice = 10.0,pointsAwarded = 3.0,createdDate = LocalDateTime.parse("2023-03-07T14:05:30.00"), price = 0.0 ,paymentMethod = PaymentMethod.MASTERCARD, paymentMetaData = PaymentMetaData(paymentModifier = 0.0, pointModifier = 0.0)),
            PaymentEntity(finalPrice = 10.0,pointsAwarded = 3.0,createdDate = LocalDateTime.parse("2023-03-07T14:50:30.00"), price = 0.0 ,paymentMethod = PaymentMethod.MASTERCARD, paymentMetaData = PaymentMetaData(paymentModifier = 0.0, pointModifier = 0.0)),
            PaymentEntity(finalPrice = 10.0,pointsAwarded = 3.0,createdDate = LocalDateTime.parse("2023-03-08T13:05:30.00"), price = 0.0 ,paymentMethod = PaymentMethod.MASTERCARD, paymentMetaData = PaymentMetaData(paymentModifier = 0.0, pointModifier = 0.0)),
            PaymentEntity(finalPrice = 10.0,pointsAwarded = 3.0,createdDate = LocalDateTime.parse("2023-03-08T17:05:30.00"), price = 0.0 ,paymentMethod = PaymentMethod.MASTERCARD, paymentMetaData = PaymentMetaData(paymentModifier = 0.0, pointModifier = 0.0)),
            PaymentEntity(finalPrice = 10.0,pointsAwarded = 3.0,createdDate = LocalDateTime.parse("2023-03-08T18:00:00.00"), price = 0.0 ,paymentMethod = PaymentMethod.MASTERCARD, paymentMetaData = PaymentMetaData(paymentModifier = 0.0, pointModifier = 0.0)),
            PaymentEntity(finalPrice = 10.0,pointsAwarded = 3.0,createdDate = LocalDateTime.parse("2023-03-08T18:30:30.00"), price = 0.0 ,paymentMethod = PaymentMethod.MASTERCARD, paymentMetaData = PaymentMetaData(paymentModifier = 0.0, pointModifier = 0.0))
            )
    }

    @Test
    fun getSales() {
        every { paymentRepository.findAllByCreatedDateBetween(any(), any(),any()) } returns paymentEntityList
        val salesData = salesService.getSales(SalesDataRequest.newBuilder().startDateTime(LocalDateTime.parse("2023-03-07T00:00:00.00")).endDateTime(LocalDateTime.parse("2023-03-08T23:59:59.00")).build())
        assertTrue(salesData.sales.size == 5)
    }
}