package br.com.treinaweb.twprojetos.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CargoDTO {

	private String nome;

    @NotNull
    @Size(min = 3, max = 40)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
