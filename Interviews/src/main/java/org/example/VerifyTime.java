package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
//Validate time in HH:MM format
// HH:mm:ss
public class VerifyTime {
    // Java Program to Validate the Pattern
    // Using Regex

    // Regex Pattern
    private static final Pattern TIME_PATTERN
            = Pattern.compile("^([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");

    static Pattern time24Hrs
            = Pattern.compile("^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");

    // Function to Validate time
    public static boolean validateTime(String timeString) {
        return time24Hrs.matcher(timeString).matches();
    }

    // Main Function
    public static void main(String[] args) {
        // Replace with the time string
        // you want to check
        String timeToCheck = "18:65:40";
        for(String temp: List.of("19:90:0", "1:1:1", "99:9:1", "18:55:40"))
        {
            if(validateTime(temp)){
                System.out.println("Valid = " + temp);
            }else {
                System.out.println("INVALID = " + temp);
            }
        }
        if (validateTime(timeToCheck)) {
            // Proceed with time-related operations if valid
            System.out.println(
                    timeToCheck + " is a valid time format.");
        } else {
            // Handle the invalid time case appropriately
            System.out.println(
                    timeToCheck
                            + " is an invalid time format.");
        }
    }
}
