package miranda.cordeiro.escola.gradecurricular.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class MateriaException extends RuntimeException{
	
	private static final long serialVersionUID = 8099852920461165617L;
	/**
	 * 
	 */
	private final HttpStatus httpStatus;
	
	public MateriaException(final String mensagem , final HttpStatus httpStatus) {
		super(mensagem) ;
		this.httpStatus = httpStatus;
	}

	
	
	
}
