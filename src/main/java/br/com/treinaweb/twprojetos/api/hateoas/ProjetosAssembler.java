package br.com.treinaweb.twprojetos.api.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.treinaweb.twprojetos.api.controles.ClienteControlerApi;
import br.com.treinaweb.twprojetos.api.controles.FuncionarioControleApi;
import br.com.treinaweb.twprojetos.api.controles.ProjetoControleApi;
import br.com.treinaweb.twprojetos.entidades.Projeto;

@Component
public class ProjetosAssembler implements SimpleRepresentationModelAssembler<Projeto> {

	@Override
	public void addLinks(EntityModel<Projeto> resource) {
		Long clienteId = resource.getContent().getCliente().getId();
		Long liderId = resource.getContent().getLider().getId();
		Long id = resource.getContent().getId();
		
		Link liderLink = linkTo(methodOn(FuncionarioControleApi.class).buscarPorId(liderId))
			.withRel("lider")
			.withType("GET");
			
		Link clientLink = linkTo(methodOn(ClienteControlerApi.class).buscarPorId(clienteId))
		  .withRel("cliente")
		  .withType("GET");
		
		Link selLink = linkTo(methodOn(ProjetoControleApi.class).buscarPorId(id))
				.withSelfRel()
				.withType("GET");
		
		Link editarLink = linkTo(methodOn(ProjetoControleApi.class).atualizar(null, id))
				.withSelfRel()
				.withType("PUT");
		Link excluirLink = linkTo(methodOn(ProjetoControleApi.class).excluirPorId(id))
				.withSelfRel()
				.withType("DELETE");
		Link equipeLink = linkTo(methodOn(ProjetoControleApi.class).buscarEquipe(id))
				.withRel("equipe")
				.withType("GET");
		
		resource.add(liderLink, clientLink, selLink, editarLink, excluirLink, equipeLink);
		
		

		
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
		Link selLink = linkTo(methodOn(ProjetoControleApi.class).buscarTodos(null))
		.withSelfRel()
		.withType("GET");
		
		resources.add(selLink);
		
	}

	
}
