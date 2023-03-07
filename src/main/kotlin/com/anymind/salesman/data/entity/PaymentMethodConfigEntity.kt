package com.anymind.salesman.data.entity

import com.anymind.generated.types.PaymentMethod
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.*


@Entity
@Table(name = "payment_method_config")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
data class PaymentMethodConfigEntity (

    @Id
    val id: Long? = null,
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    val paymentMethod: PaymentMethod,
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    val pointConfig: PointMetaData
)