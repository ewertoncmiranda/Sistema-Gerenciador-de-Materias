package miranda.cordeiro.escola.gradecurricular.service.interfaces;

import java.util.List;

import miranda.cordeiro.escola.gradecurricular.dto.MateriaDTO;
import miranda.cordeiro.escola.gradecurricular.entidade.MateriaEntity;

public interface IMateriaService {
	
	public MateriaDTO atualizar(final MateriaDTO materia) ;
	
	public Boolean excluir(final Long id) ;
	
	public MateriaDTO consultar(final Long id);
	
	public List<MateriaDTO> listar();
	
	public Boolean cadastrarMateria(final MateriaDTO materia);	
	
	

}
