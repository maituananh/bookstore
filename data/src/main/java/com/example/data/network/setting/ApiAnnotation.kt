package com.example.data.network.setting

import javax.inject.Qualifier

@Qualifier
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.BINARY)
annotation class ItBookApi

@Qualifier
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.BINARY)
annotation class CalenderApi