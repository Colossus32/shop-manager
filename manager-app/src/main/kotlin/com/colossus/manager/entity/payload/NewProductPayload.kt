package com.colossus.manager.entity.payload

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewProductPayload @JvmOverloads constructor(
    @NotNull @Size(min = 3, max = 50) var title: String = "",
    @Size(max = 1000) var details: String = ""
)

