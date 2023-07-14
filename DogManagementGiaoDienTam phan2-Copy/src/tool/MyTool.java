package tool;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MyTool {

    private int i;
    private double j;
    private String c;

    private Scanner sc = new Scanner(System.in);

    public int iInt() {
        while (true) {
            try {
                i = Integer.parseInt(sc.nextLine());
                return i;
            } catch (Exception e) {
                System.out.println("Try input againt !!");
            }

        }
    }

    public double iDouble() {
        while (true) {
            try {
                j = Double.parseDouble(sc.nextLine());
                return j;
            } catch (Exception e) {
                System.out.println("Try input againt !!");
            }

        }
    }

    public String iString() {
        while (true) {
            try {
                c = sc.nextLine();
                return c.toUpperCase();
            } catch (Exception e) {
                System.out.println("Try input againt !!");
            }

        }
    }

    public String iPhoneNum() {
        while (true) {
            try {
                System.out.print("Input phone number: ");
                c = sc.nextLine();
                Pattern p = Pattern.compile("^[0][0-9]{9}$");
                if (p.matcher(c).matches()) {
                    return c.toUpperCase();
                }
            } catch (Exception e) {
                System.out.println("Try input againt !!");
            }

        }
    }

}
