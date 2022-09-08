package com.blaja.core.mapper

import com.blaja.core.model.Model
import com.blaja.core.model.ModelItem

interface ItemMapper<M : Model, MI : ModelItem> {

   fun mapToPresentation(model: M): MI

    fun mapToDomain(modelItem: MI): M
}