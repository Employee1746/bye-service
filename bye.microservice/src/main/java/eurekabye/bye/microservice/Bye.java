package eurekabye.bye.microservice;

public class Bye {

    private String message;

    public Bye() {
    }

    public Bye(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
