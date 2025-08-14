package br.vianna.trabalho.sigei.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginViewDTO {

    private String nome;
    private int idade;

}
