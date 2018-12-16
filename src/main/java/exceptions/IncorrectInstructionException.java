package exceptions;

import static constants.Constants.INCORRECT_INSTRUCTION_MSG;

public class IncorrectInstructionException extends Exception {

    private String name;

    public IncorrectInstructionException(String name) {
        this.name = name;
    }

    public String getMessage() {
        return String.format(INCORRECT_INSTRUCTION_MSG, name);
    }
}