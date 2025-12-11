package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ModelHistoryPage;

public class ModelHistoryTest extends BaseTest {
    ModelHistoryPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new ModelHistoryPage();
    }

    @Test(priority = 1)
    public void seeModelDetails() {
        ob.seeModelDetails();
    }
}
