package miranda.cordeiro.escola.gradecurricular.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import miranda.cordeiro.escola.gradecurricular.controller.MateriaController;
import miranda.cordeiro.escola.gradecurricular.dto.MateriaDTO;
import miranda.cordeiro.escola.gradecurricular.entidade.MateriaEntity;
import miranda.cordeiro.escola.gradecurricular.exceptions.MateriaException;
import miranda.cordeiro.escola.gradecurricular.repositorio.MateriaRepository;
import miranda.cordeiro.escola.gradecurricular.service.interfaces.IMateriaService;

@Service
public class MateriaService implements IMateriaService{
	
	private MateriaRepository repositorio ;
	private ModelMapper mapper ;
	private static final String CONTACT_ADMIN = "Erro interno! Contate um administrador!"; 
	private static final String MATERIA_NOT_FOUND = "Matéria não encontrada!";
	
	@Autowired
	public MateriaService(MateriaRepository repos) {
		this.repositorio = repos;
		this.mapper = new ModelMapper();
	
	}	
	
	@Override
	public MateriaDTO atualizar( MateriaDTO materia) {
		try {
									
			Optional<MateriaEntity> materiaOptional = this.repositorio.findById(materia.getId());
				
				if(materiaOptional.isPresent()) {
															
					MateriaEntity entidade = this.mapper.map(materia , MateriaEntity.class);
															
					return mapper.map(this.repositorio.save(entidade), MateriaDTO.class);
										
				}
							
				throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Boolean excluir(Long id) {
		try {
			this.consultar(id);
			this.repositorio.deleteById(id);
			return Boolean.TRUE ;	
		
		}catch(MateriaException m) {
			throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			throw new MateriaException(CONTACT_ADMIN, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public MateriaDTO consultar(Long id) {
		
		try {
		   Optional<MateriaEntity> materiaOptional = repositorio.findById(id);
		   if(materiaOptional.isPresent()){
			 return mapper.map(materiaOptional.get() ,MateriaDTO.class);		     	
			}
			throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND);
			
		} catch (MateriaException m) {
			throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			throw new MateriaException(CONTACT_ADMIN, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}		
	}

	@Override
	public List<MateriaDTO> listar() {
		try {
			
			List <MateriaDTO> lista = new ArrayList<>();
			
			for(MateriaEntity materia : repositorio.findAll()) {
				
				MateriaDTO dto = mapper.map( materia, MateriaDTO.class) ;
				
				dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)).withSelfRel());				    
				
				lista.add(dto) ;				
				
			}
			return lista ;
				
		} catch (MateriaException e) {
			throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			throw new MateriaException(CONTACT_ADMIN, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}		
		
	}

	@Override
	public Boolean cadastrarMateria(MateriaDTO materia) {
		try {
		
			MateriaEntity entidade = this.mapper.map(materia, MateriaEntity.class);
			repositorio.save(entidade);
			return Boolean.TRUE;
		} catch (MateriaException e) {
			throw new MateriaException(CONTACT_ADMIN, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	}


}
