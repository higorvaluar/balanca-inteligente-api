package com.unitins.agrotins.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public class PesagemDTO {
    @NotNull
    public String tagRFID;
    @NotNull
    @Positive
    public Double peso;

    public LocalDateTime data;
}