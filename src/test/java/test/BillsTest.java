package test;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import utils.run.BaseTest;
import utils.run.TestUtils;

import java.util.List;

public class BillsTest extends BaseTest {
    private static final String BILLS_NAME = "NewTestBills22";

    @Description("Create new bills FI-T12")
    @Test(priority = 1)
    public void testCreateNewBills() {
        List<String> nameBill = new HomePage(getDriver())
                .goBill()
                .clickCreateBill()
                .setName(BILLS_NAME)
                .setMinimumAmount(3)
                .setMaximumAmount(5)
                .submit()
                .goBill()
                .getBillsNamesList();

        Assert.assertTrue(nameBill.contains(BILLS_NAME));
    }

    @Description("Checking the date Bills repeats monthly FI-T13")
    @Test(priority = 2)
    public void testCheckingTheDateBillsRepeatsMonthly() {
        String currentDate = TestUtils.getCurrentDate();

        List<String> nextExpectedMatch = new HomePage(getDriver())
                .goBill()
                .getNextExpectedMatch(BILLS_NAME);

        Assert.assertEquals(nextExpectedMatch, currentDate);
    }

    @Test
    public void testSetBillToRepeatWeekly() {
        List<String> currentDate = TestUtils.getWeeklyDates();

        List<String> nextExpectedMatch = new HomePage(getDriver())
                .goBill()
                .clickPencil(BILLS_NAME)
                .setRepeatsWeekly()
                .clickSubmit()
                .getNextExpectedMatch(BILLS_NAME);

        Assert.assertEquals(nextExpectedMatch, currentDate);
    }
}