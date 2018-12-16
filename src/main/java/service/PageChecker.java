package service;

import exceptions.NullPageException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

import static constants.Constants.INSTRUCTIONS_EXAMPLE;

public class PageChecker {

    private void checkNullPage(Document page) throws NullPageException {
        if (page == null) {
            throw new NullPageException(INSTRUCTIONS_EXAMPLE);
        }
    }

    public void checkLinkPresentByHref(Instruction instruction, Document page) throws NullPageException {
        checkNullPage(page);
        String cssQuery = String.format("a[href='%s']", instruction.getSecondCommand());
        List<Element> list = page.select(cssQuery);
        if (list.size() == 0) {
            ResultsRecorder.status = false;
        }
    }

    public void checkLinkPresentByName(Instruction instruction, Document page) throws NullPageException {
        checkNullPage(page);
        String cssQuery = String.format("a[name='%s']", instruction.getSecondCommand());
        List<Element> list = page.select(cssQuery);
        if (list.size() == 0) {
            ResultsRecorder.status = false;
        }
    }

    public void checkPageTitle(Instruction instruction, Document page) throws NullPageException {
        checkNullPage(page);
        String title = page.title();
        if (!title.equals(instruction.getSecondCommand())) {
            ResultsRecorder.status = false;
        }
    }

    public void checkPageContains(Instruction instruction, Document page) throws NullPageException {
        checkNullPage(page);
        List<Element> list = page.getElementsMatchingText(instruction.getSecondCommand());
        if (list.size() == 0) {
            ResultsRecorder.status = false;
        }
    }
}