package uiautomator.testing.android.example.com.uiautomatortest;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class FasTipUiTestsUtils {
    //Method to start Main Activity from Home Screen
    public static void startMainActivityFromHomeScreen(UiDevice mDevice, long LAUNCH_TIMEOUT, String BASIC_SAMPLE_PACKAGE) {
        mDevice.pressHome();
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    //Method to Navigate to Settings Activity
    public static void startSettingsActivity(UiDevice mDevice, long LAUNCH_TIMEOUT, String BASIC_SAMPLE_PACKAGE) throws UiObjectNotFoundException {
        startMainActivityFromHomeScreen(mDevice, LAUNCH_TIMEOUT, BASIC_SAMPLE_PACKAGE);
        getUiObjectFromResourceId(mDevice, TestConstants.SETTINGS_BUTTON_ID).clickAndWaitForNewWindow();
    }

    //Method to get UI object from resource ID
    public static UiObject getUiObjectFromResourceId(UiDevice mDevice, String resouceID) {
        return mDevice.findObject(new UiSelector()
                .resourceId(resouceID));
    }


    public static Double getTipPercentageValue(UiObject tipPercentageTextView) throws UiObjectNotFoundException {
        String tipPercentangeStringRepresentation = tipPercentageTextView.getText();
        Double tipPercentage = Double.parseDouble(tipPercentangeStringRepresentation.substring(0, tipPercentangeStringRepresentation.length() - 1));
        return tipPercentage;
    }

    public static Double getTipAmountValue(UiObject tipAmountTextView) throws UiObjectNotFoundException {
        String tipAmountStringRepresentation = tipAmountTextView.getText();
        Double tipAmount = Double.parseDouble(tipAmountStringRepresentation.substring(1));
        return tipAmount;

    }

    public static Double getTotalAmountValue(UiObject totalAmountTextView) throws UiObjectNotFoundException {
        String totalAmountStringRepresentation = totalAmountTextView.getText();
        Double totalAmount = Double.parseDouble(totalAmountStringRepresentation.substring(1));
        return totalAmount;
    }


}
