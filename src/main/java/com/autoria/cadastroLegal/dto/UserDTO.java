package com.autoria.cadastroLegal.dto;

import com.autoria.cadastroLegal.utils.Messages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Integer id;

    @NotNull(message = Messages.NAME_NULL)
    private String name;

    @CPF
    @NotNull(message = Messages.CPF_NULL)
    private String cpf;

    private boolean status;
}
