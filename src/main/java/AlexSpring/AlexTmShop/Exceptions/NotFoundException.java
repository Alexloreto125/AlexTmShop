package AlexSpring.AlexTmShop.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(Long id){
        super("Record con id: "+ id+ " non trovato");
    }
}
