package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DatasetsPage;

public class DatasetsTest extends BaseLibrary {
    DatasetsPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new DatasetsPage();
    }

    @Test(priority = 1)
    public void createDataset(){
        ob.createDataset();
    }
}
