package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DatasetsPage;

public class DatasetsTest extends BaseTest {
    DatasetsPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new DatasetsPage();
    }

    @Test(priority = 1)
    public void createDataset(){
        ob.createDataset();
    }
}
