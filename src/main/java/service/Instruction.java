package service;

public class Instruction {

    private String firstCommand;
    private String secondCommand;
    private String thirdCommand;

    public String getFirstCommand() {
        return firstCommand;
    }

    public String getSecondCommand() {
        return secondCommand;
    }

    public String getTimeout() {
        return thirdCommand;
    }

    public Instruction(String[] instructions) {
        this.firstCommand = instructions[0];
        this.secondCommand = instructions[1];
        this.thirdCommand = instructions[2];
    }

    @Override
    public String toString() {
        return getTimeout() == null ? "[" + firstCommand + " \"" + secondCommand + "\"] "
                : "[" + firstCommand + " \"" + secondCommand + "\" \"" + thirdCommand + "\"] ";
    }
}