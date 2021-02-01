package miranda.cordeiro.escola.gradecurricular.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import miranda.cordeiro.escola.gradecurricular.entidade.MateriaEntity;

@Repository
@Transactional
public interface MateriaRepository extends JpaRepository<MateriaEntity, Serializable> {

}
