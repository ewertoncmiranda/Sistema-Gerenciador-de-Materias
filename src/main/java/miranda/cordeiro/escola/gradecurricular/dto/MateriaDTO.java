package miranda.cordeiro.escola.gradecurricular.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MateriaDTO extends RepresentationModel<MateriaDTO> {
	
	private Long id ;
	
	@NotBlank(message = "Informe o nome da Matéria!")
	private String nome;
	
	@Min(value = 34, message = "MinÍmo de 34 horas por matéria!")	
	@Max(value = 150, message = "Máximo de 150 horas por matéria!")
	private int horas;

	@NotBlank(message = "Informe o código da Matéria!")
	private String codigo;

	@Min(value = 34, message = "Permitido o minímo 34!")	
	@Max(value = 150, message = "Permitido o maxímo de 150!")
	private int frequencia;
	 
}
