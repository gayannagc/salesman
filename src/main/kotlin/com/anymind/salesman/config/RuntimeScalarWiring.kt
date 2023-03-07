package com.anymind.salesman.config


import com.netflix.graphql.dgs.DgsComponent
import graphql.schema.GraphQLScalarType
import com.netflix.graphql.dgs.DgsRuntimeWiring
import graphql.schema.idl.RuntimeWiring

@DgsComponent
class RuntimeScalarWiring {

    private val dateTimeScalar = GraphQLScalarType.newScalar()
        .name("DateTime")
        .description("DateTime type")
        .coercing(DateTimeScalar())
        .build()

    @DgsRuntimeWiring
    fun addScalar(builder: RuntimeWiring.Builder): RuntimeWiring.Builder {
        return builder
            .scalar(dateTimeScalar)
    }
}