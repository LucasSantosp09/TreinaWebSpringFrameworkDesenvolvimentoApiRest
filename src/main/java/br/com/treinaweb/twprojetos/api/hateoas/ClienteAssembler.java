package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controles.ClienteControlerApi;
import br.com.treinaweb.twprojetos.entidades.Cliente;

@Component
public class ClienteAssembler implements SimpleRepresentationModelAssembler<Cliente> {

	@Override
	public void addLinks(EntityModel<Cliente> resource) {
		Long id = resource.getContent().getId();
		Link selfLink = linkTo(methodOn(ClienteControlerApi.class).buscarPorId(id))
				.withSelfRel()
				.withType("GET");
		resource.add(selfLink);
		
		  Link projetosLink = linkTo(methodOn(ClienteControlerApi.class).buscarProjetos(id))
		            .withRel("projetos")
		            .withType("GET");

		        resource.add(selfLink, projetosLink);
		
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Cliente>> resources) {
	      Link selfLink = linkTo(methodOn(ClienteControlerApi.class).buscarTodos(null))
	              .withSelfRel()
	              .withType("GET");
	       resources.add(selfLink);
	}



	

}
