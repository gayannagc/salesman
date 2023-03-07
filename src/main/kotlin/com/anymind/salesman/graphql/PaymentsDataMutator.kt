package com.anymind.salesman.graphql


import com.anymind.generated.DgsConstants
import com.anymind.generated.types.Payment
import com.anymind.generated.types.PaymentRequest
import com.anymind.salesman.service.SalesService


import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument


@DgsComponent
class PaymentsDataMutator (
    private val salesService: SalesService
){

    @DgsMutation(field = DgsConstants.MUTATION.CreatePayment)
    fun processPayment(@InputArgument request: PaymentRequest): Payment {
        return salesService.processPayment(request)
    }

}