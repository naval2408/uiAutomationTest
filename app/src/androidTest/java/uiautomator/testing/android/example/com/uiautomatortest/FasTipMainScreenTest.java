package uiautomator.testing.android.example.com.uiautomatortest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.getUiObjectFromResourceId;
import static uiautomator.testing.android.example.com.uiautomatortest.FasTipUiTestsUtils.startMainActivityFromHomeScreen;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.BASIC_SAMPLE_PACKAGE;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.BILL_AMOUNT_EDIT_TEXT_ID;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.LAUNCH_TIMEOUT;
import static uiautomator.testing.android.example.com.uiautomatortest.TestConstants.VALID_BILL_AMOUNT_TO_BE_TYPED;

@RunWith(AndroidJUnit4.class)
public class FasTipMainScreenTest {

    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());;

    @Before
    public void setUp() throws Exception {
        startMainActivityFromHomeScreen(mDevice,LAUNCH_TIMEOUT,BASIC_SAMPLE_PACKAGE);
    }


    @Test
    public void sampleTest() throws UiObjectNotFoundException {
        UiObject billAmountEditText =getUiObjectFromResourceId(mDevice,BILL_AMOUNT_EDIT_TEXT_ID);
        billAmountEditText.setText(VALID_BILL_AMOUNT_TO_BE_TYPED);

    }
}
