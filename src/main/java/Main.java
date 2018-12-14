import service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;

import static service.TestResult.*;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You haven't entered any arguments");
            return;
        }

        InputChecker checker = new InputChecker();

        checker.verifyCmdInput(args);

        InstructionsHandler handler = new InstructionsHandler();

        String instruction = null;
        Charset charset = Charset.forName("windows-1251");
        Path path = Paths.get(args[0]);

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            while ((instruction = reader.readLine()) != null) {
                if (instruction.equals("")) {
                    continue;
                }
                handler.performInstructions(instruction);
            }

            String userDir = System.getProperty("user.home");

            LogWriter logWriter = new LogWriter();

            logWriter.prepareData();
            logWriter.writeResult(args[1], userDir);
            logWriter.resetData();
        } catch (UnknownHostException e) {
            System.out.println("Couldn't open website with this instruction - " + instruction);
            e.printStackTrace();
        } catch (MalformedInputException e) {
            System.out.println("Turn your file with instructions in this charset - 'windows-1251' ");
            e.printStackTrace();
        } catch (NoSuchFileException e) {
            System.out.printf("You had to enter path to file with instructions as first parameter. Such file doesn't exist - %s%n", args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
