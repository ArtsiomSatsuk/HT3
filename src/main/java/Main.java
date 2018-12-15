import service.InputChecker;
import service.InstructionsHandler;
import service.LogWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static constants.Constants.*;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println(NO_ARGUMENTS_MSG);
            return;
        }

        String pathToInstructions = args[0];
        String pathToLogFile = null;

        if (args.length == 1) {
            System.out.println(NO_PATH_TO_LOG_MSG);
            pathToLogFile = DEFAULT_LOG_PATH;
        } else {
            pathToLogFile = args[1];
        }

        InputChecker checker = new InputChecker();
        checker.verifyCmdInput(args);
//if false - continue  цикле и не будет NoSuchFileException


        String instruction = null;
        Charset charset = Charset.forName("windows-1251");
        Path instrFile = Paths.get(pathToInstructions);

        InstructionsHandler instrHandler = new InstructionsHandler();

        try (BufferedReader reader = Files.newBufferedReader(instrFile, charset)) {

            while ((instruction = reader.readLine()) != null) {
                if (instruction.matches("\\s*")) {
                    continue;
                }
                instrHandler.performInstructions(instruction);
            }

            LogWriter logWriter = new LogWriter();

            logWriter.prepareData();
            logWriter.writeResult(pathToLogFile, pathToInstructions);
            logWriter.resetData();

        } catch (UnknownHostException e) {
            System.out.printf(UNKNOWN_HOST_EXC_MSG, instruction, pathToInstructions);
            e.printStackTrace();
        } catch (MalformedInputException e) {
            System.out.println(MALFORMED_INPUT_EXC_MSG);
            e.printStackTrace();
        } catch (NoSuchFileException e) {
            System.out.printf(NO_SUCH_FILE_EXC_MSG, pathToInstructions);
            e.printStackTrace();
        }
//        catch (IncorrectInstructionException e){
//
//        }
    }
}
//        try {
//            checker.verifyCmdInput(args);
//
//            Queue<String> instructions = new ArrayDeque<>(Files.readAllLines(instructionsFile, Charset.forName("windows-1251")));
//
//            while ((instruction = instructions.poll()) != null) {
//                if (instruction.equals("")) {
//                    continue;
//                }
//                handler.performInstructions(instruction);
//            }


//            String userDir = System.getProperty("user.home");
//
//            LogWriter logWriter = new LogWriter();
//
//            logWriter.prepareData();
//            logWriter.writeResult(args[1], userDir);
//            logWriter.resetData();

//        }
//        catch (UnknownHostException e) {
//            System.out.println("Couldn't open website according to this instruction - " + instruction);
//            e.printStackTrace();
//        } catch (MalformedInputException e) {
//            System.out.println("Turn your file with instructions in this charset - 'windows-1251' ");
//            e.printStackTrace();
//        } catch (NoSuchFileException e) {
//            System.out.printf("You had to enter path to file with instructions as first parameter. Such file doesn't exist - %s%n", args[0]);
//        } catch (SocketTimeoutException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
