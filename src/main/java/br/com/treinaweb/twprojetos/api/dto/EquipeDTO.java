package br.com.treinaweb.twprojetos.api.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class EquipeDTO {

	@NotNull
	@NotEmpty
	private List<Long> equipe;

	public List<Long> getEquipe() {
		return equipe;
	}

	public void setEquipe(List<Long> equipe) {
		this.equipe = equipe;
	}
	
	
}
