package AlexSpring.AlexTmShop.Exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    List<ObjectError> errorList;

    public BadRequestException(String message){
        super(message);

    }

    public BadRequestException(List<ObjectError> errorList){
        super("Errori nel payload");
        this.errorList= errorList;
        System.out.println(errorList);
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }



}
