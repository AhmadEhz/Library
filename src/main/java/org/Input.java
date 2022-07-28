package org;

import java.util.Scanner;

public class Input {
    static Scanner scanner = new Scanner(System.in);

    static String scanner() {
        return scanner.nextLine();
    }


    public static boolean checkInput(String input) {
        if (input.equals(""))
            return false;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean checkPassword(String input) {
        if (input.length() < 8) {
            Print.invalidPassword();
            return false;
        } else return true;
    }

    static boolean checkUsername(String input) {
        if (input.length() < 5) {
            Print.errorLengthUsername();
            return false;
        }
        for (int i = 0; i < input.length(); i++)
            if (!Character.isLetterOrDigit(input.charAt(i)) && input.charAt(i) != '_') {
                Print.invalidEntry();
                Utility.delay(1);
                return false;
            }
        return true;
    }
}
