package swt6.telemetry.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FindByIdException extends RuntimeException{
    public FindByIdException(Class<?> aClass, Long id){
        super(aClass.getSimpleName() + " with it " + id + " not found");
    }
}
