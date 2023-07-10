package br.com.treinaweb.twprojetos.api.excesoes;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class GerenciadorDeExcesoesApi extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiErro> handleEntityNotFoundException(EntityNotFoundException exception){
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		ApiErro apiErro = new ApiErro(
				
		httpStatus.value(),
		httpStatus.getReasonPhrase(),
		LocalDateTime.now(),
		exception.getLocalizedMessage()
	 );
		
		return new ResponseEntity<>(apiErro, httpStatus);
				
	}
}
