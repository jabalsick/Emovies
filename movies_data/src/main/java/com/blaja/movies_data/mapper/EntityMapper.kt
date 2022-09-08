package com.blaja.movies_data.mapper


import com.blaja.core.model.Model
import com.blaja.movies_data.model.ModelResponse

interface EntityMapper<M : Model, ME : ModelResponse> {

    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME
}