package abanoub.johnny.development.moviesapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    //// FIXME: 7/23/2017 fix the validations class
    private static int passLength = 8;
    public final static String emailregExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

    public static boolean emptyStringValidation(String text) {
        // equalsIgnoreCase
        if (text.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean containsDigit(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containschar(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isValidEmail(String inputEmail) {
        Pattern patternObj = Pattern.compile(emailregExpn);
        Matcher matcherObj = patternObj.matcher(inputEmail/*
                                                         * May be fetched from
														 * EditText. This string
														 * is the one which you
														 * wanna validate for
														 * email
														 */);

        return matcherObj.matches();
//        if (matcherObj.matches()) {
//
//            // Valid email wid
//            return true;
//        } else {
//            // not a valid email wid
//            return false;
//        }
    }

    public static boolean checkPassWordAndConfirmPassword(String password,
                                                          String confirmPassword) {
        boolean pstatus = false;
        if (confirmPassword != null && password != null) {
            if (password.equals(confirmPassword)) {
                // are equal
                pstatus = true;
            }
            // are not equal
        }
        return pstatus;
    }

    public static boolean validHomePhoneLength(String number) {
        if (number.length() < 7 || number.length() > 8) {
            // invalid number
            return true;
        }
        // valid number
        return false;

    }

    public static boolean validMobilePhoneLength(String number) {
        if (number.length() >= 9) {
            // invalid number
            return true;
        }
        // valid number
        return false;
    }

    public static boolean passwordLength(String number) {
        if (number.length() >= passLength) {
            // invalid number
            return true;
        }
        // valid number
        return false;
    }

    public static boolean validMobilePhoneNumber(String number) {

        if (number.charAt(0) != '0' || number.charAt(1) != '1') {
            // invalid number
            return false;
        }
        // valid number
        return true;
    }

    public static boolean isTablet(Activity context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /*
     * return true if empty or null and false if not empty
     */
    public static boolean isNotEmptyNorNull(String str) {
        if (str != null && !str.equals(""))
            return true;
        return false;

    }

    public static boolean isZero(double price) {
        if (price == 0)
            return true;
        else
            return false;

    }

    public static String isValidPhone(Context context, String number) {

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        String phnoeNumber = null;
        if (number != null) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String countryCodeValue = tm.getNetworkCountryIso().toUpperCase();
                if (countryCodeValue.isEmpty() || countryCodeValue.length() <= 0 || countryCodeValue.equals(null)) {
                    countryCodeValue = "EG";
                }
                Phonenumber.PhoneNumber numberProto = phoneUtil.parse(number, countryCodeValue);

                if (phoneUtil.isValidNumber(numberProto)) {
                    phnoeNumber = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
                }
            } catch (NumberParseException e) {
                System.err.println("NumberParseException was thrown: " + e.toString());
            }
        }

        return phnoeNumber;
    }

    public static boolean isCitySelected(String city) {
        if (city.trim().equals("-1"))
            return false;
        else
            return true;
    }

    public static boolean isRegionSelected(String region) {
        if (region.trim().equals("-1"))
            return false;
        else
            return true;
    }




}
