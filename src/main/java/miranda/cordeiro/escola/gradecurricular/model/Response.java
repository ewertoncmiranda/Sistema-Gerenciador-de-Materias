package miranda.cordeiro.escola.gradecurricular.model;

import org.springframework.hateoas.RepresentationModel;
import org.yaml.snakeyaml.representer.Representer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response<T> extends RepresentationModel<Response<T>>{
	private int statusCode ;
	private T data ;
	

}
