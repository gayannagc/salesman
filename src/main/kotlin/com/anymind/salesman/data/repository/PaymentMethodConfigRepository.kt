package com.anymind.salesman.data.repository

import com.anymind.generated.types.PaymentMethod
import com.anymind.salesman.data.entity.PaymentMethodConfigEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentMethodConfigRepository: JpaRepository<PaymentMethodConfigEntity, Long> {
    fun findByPaymentMethod(paymentMethod: PaymentMethod) : PaymentMethodConfigEntity
}