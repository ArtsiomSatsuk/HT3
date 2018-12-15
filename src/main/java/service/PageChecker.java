package service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

import static constants.Constants.example;

public class PageChecker {

    private void nullPageChecker(Document page) {
        if (page == null) {
            System.err.println("You had to open website firstly, put command 'open' in the first string" +
                    " of your instruction file, use the following example:" + example);
            System.exit(-1);
        }
    }

    public void checkLinkPresentByHref(Instruction instruction, Document page) {
        nullPageChecker(page);
        String cssQuery = String.format("a[href='%s']", instruction.getSecondCommand());
        List<Element> list = page.select(cssQuery);
        if (list.size() == 0) {
            ResultsRecorder.status = false;
        }
    }

    public void checkLinkPresentByName(Instruction instruction, Document page) {
        nullPageChecker(page);
        String cssQuery = String.format("a[name='%s']", instruction.getSecondCommand());
        List<Element> list = page.select(cssQuery);
        if (list.size() == 0) {
            ResultsRecorder.status = false;
        }
    }

    public void checkPageTitle(Instruction instruction, Document page) {
        nullPageChecker(page);
        String title = page.title();
        if (!title.equals(instruction.getSecondCommand())) {
            ResultsRecorder.status = false;
        }
    }

    public void checkPageContains(Instruction instruction, Document page) {
        nullPageChecker(page);
        List<Element> list = page.getElementsMatchingText(instruction.getSecondCommand());
        if (list.size() == 0) {
            ResultsRecorder.status = false;
        }
    }
}