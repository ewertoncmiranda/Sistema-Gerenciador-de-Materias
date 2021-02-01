package miranda.cordeiro.escola.gradecurricular.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import miranda.cordeiro.escola.gradecurricular.exceptions.MateriaException;
import miranda.cordeiro.escola.gradecurricular.model.ErrorMapResponse;
import miranda.cordeiro.escola.gradecurricular.model.ErrorMapResponse.ErrorMapResponseBuilder;
import miranda.cordeiro.escola.gradecurricular.model.ErrorResponse;
import miranda.cordeiro.escola.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exc){
		
		Map <String , String> mapaDeErros = new HashMap<>();
		
		exc.getBindingResult().getAllErrors().forEach(
		erro ->{
			String campo = ((FieldError)erro).getField() ;
			String mensagem = erro.getDefaultMessage();
			mapaDeErros.put(campo, mensagem);
		});
		
		ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
		
		errorMap.mapa(mapaDeErros)
				.timeStamp(System.currentTimeMillis())
				.httpResponse(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
	}
	
	@ExceptionHandler(MateriaException.class)
	public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException exc){
		ErrorResponseBuilder erro = ErrorResponse.builder();
		erro.httpStatus(exc.getHttpStatus().value());
		erro.mensagem(exc.getMessage());
		erro.timeStamp(System.currentTimeMillis());
		return ResponseEntity.status(exc.getHttpStatus()).body(erro.build() );
	}

}
