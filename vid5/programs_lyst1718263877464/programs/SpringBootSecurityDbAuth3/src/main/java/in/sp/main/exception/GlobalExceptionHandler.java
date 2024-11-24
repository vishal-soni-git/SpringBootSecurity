package in.sp.main.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex)
	{
		Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
		logger.error("Exception Occured : "+ex);
		
		return new ResponseEntity<>("Exception Occured : "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}