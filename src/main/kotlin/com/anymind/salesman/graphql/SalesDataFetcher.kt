package com.anymind.salesman.graphql

import com.anymind.generated.DgsConstants
import com.anymind.generated.types.Payment
import com.anymind.generated.types.SalesData
import com.anymind.generated.types.SalesDataRequest
import com.anymind.salesman.service.SalesService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsEnableDataFetcherInstrumentation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class SalesDataFetcher(
    private val salesService: SalesService
) {

    @DgsQuery(field = DgsConstants.QUERY.GetSalesData)
    fun processPayment(@InputArgument request: SalesDataRequest): SalesData {
        return salesService.getSales(request)
    }
}