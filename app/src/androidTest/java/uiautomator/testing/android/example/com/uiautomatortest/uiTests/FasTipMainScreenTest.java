package uiautomator.testing.android.example.com.uiautomatortest.uiTests;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.getTipAmountValue;
import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.getTipPercentageValue;
import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.getTotalAmountValue;
import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.getUiObjectFromResourceId;
import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.startMainActivityFromHomeScreen;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.BASIC_SAMPLE_PACKAGE;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.BILL_AMOUNT_EDIT_TEXT_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.CALCULATE_TIP_BUTTON_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.LAUNCH_TIMEOUT;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.TIP_AMOUNT_TEXT_VIEW_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.TIP_PERCENTAGE_TEXT_VIEW_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.TOTAL_AMOUNT_TEXT_VIEW_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.VALID_BILL_AMOUNT_TO_BE_TYPED;


@RunWith(AndroidJUnit4.class)

/*In this class, we will check that the Total Amount and Tip Amount are
calculated correctly.*/

public class FasTipMainScreenTest {
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private UiObject billAmountEditText;
    private UiObject calculateTipButton;
    private UiObject tipPercentageTextView;
    private UiObject tipAmountTextView;
    private UiObject totalAmountTextView;


    //Launching the Home Activity
    @Before
    public void setUp() throws Exception {
        startMainActivityFromHomeScreen(mDevice, LAUNCH_TIMEOUT, BASIC_SAMPLE_PACKAGE);
        billAmountEditText = getUiObjectFromResourceId(mDevice, BILL_AMOUNT_EDIT_TEXT_ID);
        calculateTipButton = getUiObjectFromResourceId(mDevice, CALCULATE_TIP_BUTTON_ID);
        tipPercentageTextView = getUiObjectFromResourceId(mDevice, TIP_PERCENTAGE_TEXT_VIEW_ID);
        tipAmountTextView = getUiObjectFromResourceId(mDevice, TIP_AMOUNT_TEXT_VIEW_ID);
        totalAmountTextView = getUiObjectFromResourceId(mDevice, TOTAL_AMOUNT_TEXT_VIEW_ID);
    }


    //Validating the values of Tip Amount and Bill Amount
    @Test
    public void enterValidBillAmountAndCalculateTipTest() throws UiObjectNotFoundException {
        billAmountEditText.setText(VALID_BILL_AMOUNT_TO_BE_TYPED);
        Double amountTyped = Double.parseDouble(VALID_BILL_AMOUNT_TO_BE_TYPED);
        calculateTipButton.click();
        validateExpectedAndActualAmount(amountTyped);

    }

    //Removing Activity from Foreground
    @After
    public void tearDown() throws Exception {
        mDevice.pressBack();
    }


    private void validateExpectedAndActualAmount(Double amountTyped) throws UiObjectNotFoundException {
        Double tipPercentage = getTipPercentageValue(tipPercentageTextView);
        Double tipAmount = getTipAmountValue(tipAmountTextView);
        Double totalAmount = getTotalAmountValue(totalAmountTextView);
        BigDecimal expectedTipAmount = BigDecimal.valueOf((amountTyped * (tipPercentage)) / 100);
        BigDecimal actualTipAmount = BigDecimal.valueOf(tipAmount);
        BigDecimal expectedTotalAmount = BigDecimal.valueOf((amountTyped * (tipPercentage)) / 100 + amountTyped);
        BigDecimal actualTotalAmount = BigDecimal.valueOf(totalAmount);
        Assert.assertEquals(expectedTipAmount, actualTipAmount);
        Assert.assertEquals(expectedTotalAmount, actualTotalAmount);
    }


}
