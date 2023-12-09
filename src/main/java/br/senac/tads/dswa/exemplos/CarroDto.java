package br.senac.tads.dswa.exemplos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarroDto(

        @NotBlank(message = "Informe um modelo")
        String modelo,
        @NotBlank(message = "Informe um fabricante")
        String fabricante,
        @NotNull(message = "Informe um ano de lançamento")
        Integer anoLancamento) {
}
