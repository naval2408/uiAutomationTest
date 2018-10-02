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

import uiautomator.testing.android.example.com.uiautomatortest.TestConstants;

import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.getTipPercentageValue;
import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.getUiObjectFromResourceId;
import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.startSettingsActivity;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.ALERT_BUTTON_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.ALERT_TEXT_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.ALERT_TITLE_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.BASIC_SAMPLE_PACKAGE;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.LAUNCH_TIMEOUT;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.VALID_TIP_PERCENTAGE_TO_BE_TYPED;

@RunWith(AndroidJUnit4.class)
/*In this class, we will check that Tip Percentage is updated correctly
and user is not able to leave Tip Percentage Blank*/
public class FasTipSettingsScreenTest {
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private UiObject tipPercentageEditText;
    private UiObject saveSettingsButton;
    private UiObject alertBoxTitle;
    private UiObject alertBoxText;
    private UiObject alertBoxButton;

    @Before
    public void setUp() throws UiObjectNotFoundException {
        startSettingsActivity(mDevice, LAUNCH_TIMEOUT, BASIC_SAMPLE_PACKAGE);
        tipPercentageEditText = getUiObjectFromResourceId(mDevice, TestConstants.TIP_PERCENTAGE_EDIT_TEXT_ID);
        saveSettingsButton = getUiObjectFromResourceId(mDevice, TestConstants.SAVE_SETTINGS_BUTTON_ID);

    }

    //This test will change the Tip Percentage and validates that updated value reflects at main screen.
    @Test
    public void changeTipPercentageTest() throws UiObjectNotFoundException {
        tipPercentageEditText.setText(VALID_TIP_PERCENTAGE_TO_BE_TYPED);
        saveSettingsButton.click();
        Double tipPresentOnMainScreen = getTipPercentageValue(getUiObjectFromResourceId(mDevice, TestConstants.TIP_PERCENTAGE_TEXT_VIEW_ID));
        BigDecimal expectedPercentage = BigDecimal.valueOf(Double.parseDouble(VALID_TIP_PERCENTAGE_TO_BE_TYPED));
        BigDecimal actualPercentage = BigDecimal.valueOf(tipPresentOnMainScreen);
        Assert.assertEquals(expectedPercentage, actualPercentage);
    }

    //This test will set the Tip Percentage to Empty and validate there is proper error handling for same.
    @Test
    public void enterEmptyTipPercentageTest() throws UiObjectNotFoundException {

        tipPercentageEditText.clearTextField();
        saveSettingsButton.click();
        alertBoxTitle = getUiObjectFromResourceId(mDevice, ALERT_TITLE_ID);
        alertBoxText = getUiObjectFromResourceId(mDevice, ALERT_TEXT_ID);
        alertBoxButton = getUiObjectFromResourceId(mDevice, ALERT_BUTTON_ID);
        Assert.assertTrue(alertBoxTitle.exists());
        Assert.assertTrue(alertBoxText.exists());
        Assert.assertTrue(alertBoxButton.exists());
        alertBoxButton.click();
        mDevice.pressBack();
    }

    //Removing Activity from Foreground
    @After
    public void tearDown() throws Exception {
        mDevice.pressBack();
    }
}
