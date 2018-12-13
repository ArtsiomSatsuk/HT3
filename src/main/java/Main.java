import service.InstructionsHandler;
import service.InputChecker;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {

        InputChecker checker = new InputChecker();
        checker.verifyCmdInput(args);

        Path instructionsFile = Paths.get(args[0]);
        String instruction = null;

        try {
            Queue<String> instructions = new ArrayDeque<>(Files.readAllLines(instructionsFile, Charset.defaultCharset()));
            InstructionsHandler handler = new InstructionsHandler();

            while ((instruction = instructions.poll()) != null) {
                if (instruction.equals("")) {
                    continue;
                }
                handler.performInstructions(instruction);

            }
        } catch (UnknownHostException e) {
            System.out.println("Couldn't open website according to this instruction - " + instruction);
            e.printStackTrace();
        } catch (MalformedInputException e) {
            System.out.println("Turn your file with instructions in this charset - " + Charset.defaultCharset());
            e.printStackTrace();
        }

        //будет вывод logBuffer в файл

    }
}
