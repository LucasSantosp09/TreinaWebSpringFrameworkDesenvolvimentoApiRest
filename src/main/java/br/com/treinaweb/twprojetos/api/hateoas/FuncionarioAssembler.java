package br.com.treinaweb.twprojetos.api.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.treinaweb.twprojetos.api.controles.CargoControleApi;
import br.com.treinaweb.twprojetos.api.controles.FuncionarioControleApi;
import br.com.treinaweb.twprojetos.entidades.Funcionario;

@Component
public class FuncionarioAssembler implements SimpleRepresentationModelAssembler<Funcionario> {

	@Override
	public void addLinks(EntityModel<Funcionario> resource) {
		Long cargoId = resource.getContent().getCargo().getId();
		Long id = resource.getContent().getId();
		
		Link cargoLink = linkTo(methodOn(CargoControleApi.class).buscarPorId(cargoId))
				.withRel("cargo")
				.withType("GET");
		
		Link selfLink = linkTo(methodOn(FuncionarioControleApi.class).buscarPorId(id))
				.withSelfRel()
				.withType("GET");
		
		resource.add(cargoLink, selfLink);		
		
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Funcionario>> resources) {
		Link selLink = linkTo(methodOn(FuncionarioControleApi.class).buscarTodos(null))
				.withSelfRel()
				.withType("GET");
		
		resources.add(selLink);
		
	}

}
