import exceptions.IncorrectInstructionException;
import exceptions.NullPageException;
import service.InputHandler;
import service.InstructionsHandler;
import service.LogWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.*;

import static constants.Constants.*;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println(NO_ARGUMENTS_MSG + INPUT_EXAMPLE);
            return;
        }

        InputHandler handler = new InputHandler();

        for (String parameter : args) {
            String pathToLogFile = null;
            String pathToInstructions = null;

            String[] paths = handler.getPaths(parameter);

            if (paths[0] == null) {
                pathToInstructions = parameter;
            } else {
                pathToInstructions = paths[0];
            }

            try {
                if (!handler.verifyCmdInput(paths[0])) {
                    continue;
                }
            } catch (InvalidPathException e) {
                System.out.println(INVALID_PATH_EXC_MSG + INPUT_EXAMPLE);
                e.printStackTrace();
                continue;
            }

            if (paths[1] == null) {
                System.out.printf(NO_PATH_TO_LOG_MSG, pathToInstructions);
                pathToLogFile = DEFAULT_LOG_PATH;
            } else {
                pathToLogFile = paths[1];
            }

            String instruction = null;
            Charset charset = Charset.forName(CHARSET_NAME);
            Path path = Paths.get(pathToInstructions);

            InstructionsHandler instrHandler = new InstructionsHandler();

            try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

                while ((instruction = reader.readLine()) != null) {
                    if (instruction.matches("\\s*")) {
                        continue;
                    }
                    instrHandler.performInstructions(instruction);
                }

            } catch (UnknownHostException e) {
                System.out.printf(UNKNOWN_HOST_EXC_MSG, instruction, pathToInstructions);
                e.printStackTrace();
                continue;
            } catch (MalformedInputException e) {
                System.out.printf(MALFORMED_INPUT_EXC_MSG, pathToInstructions);
                e.printStackTrace();
                continue;
            } catch (NoSuchFileException e) {
                System.out.printf(NO_SUCH_FILE_EXC_MSG, pathToInstructions);
                e.printStackTrace();
                continue;
            } catch (IncorrectInstructionException | NullPageException e) {
                System.out.printf(FILE_WILL_NOT_BE_PROCESSED_MSG, pathToInstructions);
                e.printStackTrace();
                continue;
            }

            LogWriter logWriter = new LogWriter();

            logWriter.prepareData();
            logWriter.writeResult(pathToLogFile, pathToInstructions);
            logWriter.resetData();

        }
    }
}
