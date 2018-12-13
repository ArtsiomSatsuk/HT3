package service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

import static constants.Constants.example;

public class PageChecker {


    private void nullChecker(Document page){
        if (page == null){
            System.err.println("You had to open website firstly, put command 'open' in the first string" +
                    " of your instruction file, use the following example:" + example);
            System.exit(-1);
        }
    }

    public void checkLinkPresentByHref(Instruction instruction, Document page) {
        nullChecker(page);
        String cssQuery = "a[href='"+instruction.getSecondCommand()+"']";
        List<Element> list = page.select(cssQuery);
        if (list.size() == 0) {
            TestResult.status=false;
        }
    }

    public void checkLinkPresentByName(Instruction instruction, Document page) {
        nullChecker(page);
        String cssQuery = "a[name='"+instruction.getSecondCommand()+"']";
        List<Element> list = page.select(cssQuery);
        if (list.size() == 0) {
            TestResult.status=false;
        }
    }

    public void checkPageTitle(Instruction instruction, Document page){
        nullChecker(page);
        String title = page.title();
        if (!title.equals(instruction.getSecondCommand())) {
            TestResult.status=false;
        }
    }

    public void checkPageContains(Instruction instruction, Document page){
        nullChecker(page);
        boolean result;
        result = page.hasText();
        if (result==false) {
            TestResult.status=false;
        }
    }
}