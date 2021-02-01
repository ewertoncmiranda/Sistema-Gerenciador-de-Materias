package miranda.cordeiro.escola.gradecurricular.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miranda.cordeiro.escola.gradecurricular.dto.MateriaDTO;
import miranda.cordeiro.escola.gradecurricular.entidade.MateriaEntity;
import miranda.cordeiro.escola.gradecurricular.model.Response;
import miranda.cordeiro.escola.gradecurricular.service.MateriaService;

@RestController
@RequestMapping(name = "/materia")
public class MateriaController {

	@Autowired
	MateriaService repositorio;

	@GetMapping
	public ResponseEntity<Response <List<MateriaDTO>>> listarTodos() {
		
		Response<List<MateriaDTO>> resposta = new Response<>();
		
		resposta.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
				.methodOn(MateriaController.class)
				.cadastrarMateria(null)).withRel("LISTAR TODAS AS MATÉRIAS "));
		
		resposta.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
				.methodOn(MateriaController.class)
				.editarMateria(null)).withRel("EDITAR MATÉRIA"));
		
		resposta.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
				.methodOn(MateriaController.class)
				.excluirMateria(null)).withRel("EXCLUIR MATÉRIA "));
		
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<MateriaDTO>> consultarmateria(@PathVariable Long id) {
		
		Response <MateriaDTO> response = new Response<>();
		response.setData(this.repositorio.consultar(id));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo
				   (WebMvcLinkBuilder.methodOn(MateriaController.class).consultarmateria(id)).withSelfRel()) ;
		response.add(WebMvcLinkBuilder.linkTo
				    (WebMvcLinkBuilder.methodOn(MateriaController.class)
				    .excluirMateria(id)).withRel("DELETAR"));
		response.add(WebMvcLinkBuilder.linkTo
			    (WebMvcLinkBuilder.methodOn(MateriaController.class)
			    .listarTodos()).withRel("LISTAR TODOS"));
	
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	public ResponseEntity<Response<Boolean>> cadastrarMateria(@Valid @RequestBody MateriaDTO entidade) {
		
		Response<Boolean> resposta = new Response<>();
		
		resposta.setData(repositorio.cadastrarMateria(entidade));
		resposta.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
				.methodOn(MateriaController.class)
				.cadastrarMateria(null)).withRel("LISTAR TODAS AS MATÉRIAS "));		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(this.repositorio.excluir(id));
	}
	
	@PutMapping
	public ResponseEntity<MateriaDTO> editarMateria(@Valid @RequestBody MateriaDTO materia){
		return ResponseEntity.status(HttpStatus.OK).body(this.repositorio.atualizar(materia));
	}

}
