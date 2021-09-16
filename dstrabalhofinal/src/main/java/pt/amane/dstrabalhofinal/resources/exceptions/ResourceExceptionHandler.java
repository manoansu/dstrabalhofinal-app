package pt.amane.dstrabalhofinal.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pt.amane.dstrabalhofinal.services.exceptions.DataBaseIntegrityViolationException;
import pt.amane.dstrabalhofinal.services.exceptions.ObjectNotFoundException;



@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(Instant.now(), status.value(), "Resource not found",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DataBaseIntegrityViolationException.class)
	public ResponseEntity<StandardError> dabase(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError(Instant.now(), status.value(), "Data Base exception",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

}
