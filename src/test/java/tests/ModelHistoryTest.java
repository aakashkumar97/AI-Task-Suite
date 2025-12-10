package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ModelHistoryPage;

public class ModelHistoryTest extends BaseLibrary {
    ModelHistoryPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new ModelHistoryPage();
    }

    @Test(priority = 1)
    public void seeModelDetails() {
        ob.seeModelDetails();
    }
}
