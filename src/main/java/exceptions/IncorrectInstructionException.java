package exceptions;

public class IncorrectInstructionException extends RuntimeException{

    private String name;

    public IncorrectInstructionException(String name) {
        this.name = name;
    }

    public String getMessage() {
        return "[There is no such command. - " + name+"]\nUse the following example: \nopen \"http:www.google.com\" \"3\"" +
                "\ncheckPageTitle \"Google Search Page\" \ncheckPageContains \"The best search engine\"";
    }
}