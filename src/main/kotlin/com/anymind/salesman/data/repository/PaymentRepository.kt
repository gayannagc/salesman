package com.anymind.salesman.data.repository

import com.anymind.salesman.data.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<PaymentEntity, Long> {
}