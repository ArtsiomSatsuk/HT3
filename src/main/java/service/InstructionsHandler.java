package service;

import exceptions.IncorrectInstructionException;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.RegularExpressions.*;

public class InstructionsHandler {

    private List<Pattern> patterns = new ArrayList<>();

    private void putPatternsInCollection() {
        patterns.add(openWithIntTimeoutPattern);
        patterns.add(openWithFloatTimeoutPattern);
        patterns.add(openWithoutTimeoutPattern);
        patterns.add(checkLinkPresentByHrefPattern);
        patterns.add(checkLinkPresentByNamePattern);
        patterns.add(checkPageTitlePattern);
        patterns.add(checkPageContainsPattern);
    }


    private String[] separateCommands(String instrString) throws IncorrectInstructionException {
        String[] separatedInstr = new String[3];
        putPatternsInCollection();
        for (Pattern pattern : patterns) {
            final Matcher matcher = pattern.matcher(instrString);
            if (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    separatedInstr[i - 1] = matcher.group(i);
                }
            }
        }
        if ((separatedInstr[0] == null) || (separatedInstr[1] == null)) {
            throw new IncorrectInstructionException(instrString);
        }
        return separatedInstr;
    }

    private UrlOpener urlOpener = new UrlOpener();
    private PageChecker pageChecker = new PageChecker();
    private Document page = null;

    public void performInstructions(String instrString) throws IOException, UnknownHostException, SocketTimeoutException {
        Instruction instruction = new Instruction(separateCommands(instrString));

        switch (instruction.getFirstCommand()) {
            case "open":
                Timer.start();
                page = urlOpener.openUrl(instruction);
                Timer.stop(instruction);
                break;
            case "checkLinkPresentByHref":
                Timer.start();
                pageChecker.checkLinkPresentByHref(instruction, page);
                Timer.stop(instruction);
                break;
            case "checkLinkPresentByName":
                Timer.start();
                pageChecker.checkLinkPresentByName(instruction, page);
                Timer.stop(instruction);
                break;
            case "checkPageTitle":
                Timer.start();
                pageChecker.checkPageTitle(instruction, page);
                Timer.stop(instruction);
                break;
            case "checkPageContains":
                Timer.start();
                pageChecker.checkPageContains(instruction, page);
                Timer.stop(instruction);
                break;
        }
    }
}