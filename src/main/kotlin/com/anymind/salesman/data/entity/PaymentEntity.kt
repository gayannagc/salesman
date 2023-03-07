package com.anymind.salesman.data.entity


import com.anymind.generated.types.PaymentMethod
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.*


@Entity
@Table(name = "payments")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
data class PaymentEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    val price: Double,
    @Column(nullable = false)
    val finalPrice: Double,
    @Column(nullable = false)
    val pointsAwarded : Double,
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    val paymentMethod: PaymentMethod,
    @Column(nullable = false)
    val createdDate: LocalDateTime = LocalDateTime.now(),
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    val paymentMetaData: PaymentMetaData
)