package com.anymind.salesman.data.repository

import com.anymind.salesman.data.entity.PaymentEntity
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import javax.swing.SortOrder

interface PaymentRepository : JpaRepository<PaymentEntity, Long> {

    fun findAllByCreatedDateBetween(startDate: LocalDateTime,endDate: LocalDateTime, sortOrder: Sort): List<PaymentEntity>
}