package uz.pdp.simline.util;

import java.util.Scanner;

public class PhoneNumberPricing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the phone number:");
            String phoneNumber = in.nextLine();
            System.out.println("Price: " + calculatePrice(phoneNumber) + " sum");
        }
    }

    public static int calculatePrice(String phoneNumber) {
        if (phoneNumber.length() != 7 || !phoneNumber.matches("[0-9]+"))
            throw new IllegalArgumentException("Invalid phoneNumber");

        if (phoneNumber.matches("0000000"))
            return 52625000;
        else if (phoneNumber.matches("0007777") || (phoneNumber.matches("00\\d0000"))
                 || phoneNumber.matches("00(\\d)\\1{4}") || phoneNumber.matches("(\\d)\\1{6}")
                 || phoneNumber.matches("0777777"))
            return 50000000;
        else if (phoneNumber.matches("000(\\d)\\1{3}") || phoneNumber.matches("0000077")
                 || phoneNumber.matches("000000\\d") || phoneNumber.matches("00000\\d0")
                 || phoneNumber.matches("0000\\d00") || phoneNumber.matches("000\\d000")
                 || phoneNumber.matches("0070007") || phoneNumber.matches("\\d\\d00000")
                 || phoneNumber.matches("\\d\\d77777") || phoneNumber.matches("\\d(\\d)\\1{5}")
                 || phoneNumber.matches("(\\d)\\1{5}\\d"))
            return 30000000;
        else if (phoneNumber.matches("00\\d(\\d)\\1{3}") || phoneNumber.matches("00(\\d)000\\1")
                 || phoneNumber.matches("0070707") || phoneNumber.matches("0077000")
                 || phoneNumber.matches("0000777") || phoneNumber.matches("0007007")
                 || phoneNumber.matches("0000707") || phoneNumber.matches("0007070")
                 || phoneNumber.matches("0000770") || phoneNumber.matches("0007770")
                 || phoneNumber.matches("0007700") || phoneNumber.matches("00000(\\d)\\1")
                 || phoneNumber.matches("(\\d)\\1\\d\\1{4}")
                 || phoneNumber.matches("\\d\\d(\\d)\\1{4}")
                 || phoneNumber.matches("(\\d)(\\d)\\2{4}\\1")
                 || phoneNumber.matches("(\\d)\\1{2}\\d\\1{3}")
                 || phoneNumber.matches("\\d{3}7777")
                 || phoneNumber.matches("\\d{3}0000"))
            return 20000000;
        else if (phoneNumber.matches("0000(\\d)\\1{2}") || phoneNumber.matches("000(\\d)00\\1")
                 || phoneNumber.matches("0000(\\d)0\\1") || phoneNumber.matches("000(\\d)0\\10")
                 || phoneNumber.matches("000(\\d)\\100") || phoneNumber.matches("0000(\\d)\\10")
                 || phoneNumber.matches("000(\\d)\\1{2}0") || phoneNumber.matches("00000\\d\\d")
                 || phoneNumber.matches("00(\\d)0\\10\\1") || phoneNumber.matches("00(\\d)\\1000")
                 || phoneNumber.matches("0077070") || phoneNumber.matches("0077700")
                 || phoneNumber.matches("0077007") || phoneNumber.matches("0070770")
                 || phoneNumber.matches("0070077") || phoneNumber.matches("0070777")
                 || phoneNumber.matches("0077770")
                 || phoneNumber.matches("\\d(\\d)(\\d)\\1\\2\\1\\2")
                 || phoneNumber.matches("(\\d)\\1{3}\\d\\1{2}")
                 || phoneNumber.matches("\\d00000\\d")
                 || phoneNumber.matches("\\d77777\\d")
                 || phoneNumber.matches("(\\d)\\1{4}\\d\\1")
                 || phoneNumber.matches("(\\d)\\1{4}(\\d)\\2")
                 || phoneNumber.matches("\\d{3}(\\d)\\1{3}"))
            return 10000000;
        else if (phoneNumber.matches("0001234") || phoneNumber.matches("0007077")
                 || phoneNumber.matches("0007707") || phoneNumber.matches("00(\\d)\\10\\10")
                 || phoneNumber.matches("00(\\d)\\1{2}00") || phoneNumber.matches("00(\\d)\\100\\1")
                 || phoneNumber.matches("00(\\d)0\\1\\10") || phoneNumber.matches("00(\\d)00\\1{2}")
                 || phoneNumber.matches("00(\\d)0\\1{3}") || phoneNumber.matches("00(\\d)\\1{3}0")
                 || phoneNumber.matches("00\\d000\\d") || phoneNumber.matches("00\\d00\\d0")
                 || phoneNumber.matches("00\\d0\\d00") || phoneNumber.matches("00\\d\\d000")
                 || phoneNumber.matches("0077707") || phoneNumber.matches("0077077")
                 || phoneNumber.matches("\\d{4}000") || phoneNumber.matches("\\d{3}0\\d00")
                 || phoneNumber.matches("\\d{3}00\\d0") || phoneNumber.matches("\\d{3}000\\d")
                 || phoneNumber.matches("\\d(\\d)\\1{2}\\d\\1{2}") || phoneNumber.matches("\\d(\\d)\\1{3}\\d\\1")
                 || phoneNumber.matches("\\d(\\d)\\1\\d\\1{3}") || phoneNumber.matches("(\\d)\\1{4}\\d\\d")
                 || phoneNumber.matches("\\d(\\d)\\1{4}\\d") || phoneNumber.matches("\\d{2}(\\d)0\\10\\1"))
            return 1500000;
        else if (phoneNumber.matches("000\\d\\d00") || phoneNumber.matches("000(\\d)(\\d)\\1\\2")
                 || phoneNumber.matches("000(\\d)(\\d)\\2\\1") || phoneNumber.matches("000(\\d)\\1(\\d)\\2")
                 || phoneNumber.matches("000(\\d)\\1{2}\\d") || phoneNumber.matches("000\\d(\\d)\\1{2}")
                 || phoneNumber.matches("000\\d00\\d") || phoneNumber.matches("000(\\d)0\\1{2}")
                 || phoneNumber.matches("000(\\d)\\10\\1") || phoneNumber.matches("0000\\d0\\d")
                 || phoneNumber.matches("0000\\d\\d0") || phoneNumber.matches("00(\\d)\\1{2}0\\1")
                 || phoneNumber.matches("00(\\d)\\10\\1{2}") || phoneNumber.matches("00\\d1234")
                 || phoneNumber.matches("00(\\d)(\\d)\\1\\2\\1") || phoneNumber.matches("00(\\d)\\d\\1{3}")
                 || phoneNumber.matches("00(\\d)\\1{3}\\d") || phoneNumber.matches("00\\d(\\d)0\\10")
                 || phoneNumber.matches("00\\d0(\\d)0\\1") || phoneNumber.matches("00\\d(\\d)00\\1")
                 || phoneNumber.matches("00\\d0(\\d)\\10") || phoneNumber.matches("00\\d(\\d)\\1{2}0")
                 || phoneNumber.matches("00\\d0(\\d)\\1{2}") || phoneNumber.matches("\\d{3}(\\d)00\\1")
                 || phoneNumber.matches("\\d{3}0(\\d)\\10") || phoneNumber.matches("\\d{3}(\\d)\\1{2}0")
                 || phoneNumber.matches("\\d{3}0(\\d)\\1{2}") || phoneNumber.matches("\\d{3}0(\\d)0\\1")
                 || phoneNumber.matches("\\d{3}(\\d)0\\10") || phoneNumber.matches("\\d{2}(\\d)(\\d)\\1\\2\\1")
                 || phoneNumber.matches("\\d(\\d)\\d\\1\\d\\1\\d") || phoneNumber.matches("\\d(\\d)\\1(\\d)\\2(\\d)\\3")
                 || phoneNumber.matches("\\d(\\d)(\\d)\\1\\200") || phoneNumber.matches("\\d(\\d)(\\d)\\2\\100")
                 || phoneNumber.matches("\\d(\\d)(\\d)00\\1\\2") || phoneNumber.matches("\\d(\\d)(\\d)\\2\\1\\1\\2")
                 || phoneNumber.matches("\\d(\\d)(\\d)\\1\\2\\2\\1"))
            return 500000;
        else if (phoneNumber.matches("000(\\d)\\d\\1{2}") || phoneNumber.matches("000(\\d)\\1\\d\\1")
                 || phoneNumber.matches("000\\d0\\d0") || phoneNumber.matches("0000\\d\\d\\d")
                 || phoneNumber.matches("00\\d\\d\\d00") || phoneNumber.matches("00\\d(\\d)(\\d)\\1\\2")
                 || phoneNumber.matches("00\\d(\\d)(\\d)\\2\\1") || phoneNumber.matches("00\\d(\\d)\\1(\\d)\\2")
                 || phoneNumber.matches("00\\d{2}(\\d)\\1{2}") || phoneNumber.matches("00\\d(\\d)\\1{2}\\d")
                 || phoneNumber.matches("00(\\d)\\1\\d\\1{2}") || phoneNumber.matches("00(\\d)\\1{2}\\d\\1")
                 || phoneNumber.matches("00\\d00\\d\\d") || phoneNumber.matches("00(\\d)\\d00\\1")
                 || phoneNumber.matches("00(\\d)\\100\\d") || phoneNumber.matches("00\\d0\\d0\\d")
                 || phoneNumber.matches("00\\d(\\d)0\\1{2}") || phoneNumber.matches("00\\d(\\d)\\10\\1")
                 || phoneNumber.matches("\\d{3}(\\d)(\\d)\\2\\1") || phoneNumber.matches("\\d{3}(\\d)\\1{2}\\d")
                 || phoneNumber.matches("\\d{3}(\\d)(\\d)\\1\\2") || phoneNumber.matches("\\d{4}(\\d)\\1{2}")
                 || phoneNumber.matches("\\d{3}(\\d)\\100") || phoneNumber.matches("\\d{3}00(\\d)\\1")
                 || phoneNumber.matches("\\d{3}(\\d)\\1(\\d)\\2") || phoneNumber.matches("\\d(\\d)(\\d)\\1{2}\\2\\1")
                 || phoneNumber.matches("\\d(\\d)(\\d)\\1\\2\\1{2}") || phoneNumber.matches("\\d(\\d)(\\d)\\100\\2")
                 || phoneNumber.matches("\\d(\\d)(\\d)\\2\\2\\1\\2") || phoneNumber.matches("\\d(\\d)(\\d)\\2\\1\\2{2}")
                 || phoneNumber.matches("\\d(\\d)(\\d)\\200\\1") || phoneNumber.matches("(\\d)(\\d)(\\d)0\\1\\2\\3")
                 || phoneNumber.matches("(\\d)(\\d)(\\d)\\1\\2\\30") || phoneNumber.matches("\\d{3}1234")
                 || phoneNumber.matches("\\d{3}(\\d)0\\1{2}") || phoneNumber.matches("\\d{3}(\\d)\\10\\1"))
            return 250000;
        else if (phoneNumber.matches("\\d{5}00") || phoneNumber.matches("\\d{3}00\\d{2}")
                 || phoneNumber.matches("\\d{3}0\\d0\\d") || phoneNumber.matches("\\d{3}(\\d)\\1\\d\\1")
                 || phoneNumber.matches("\\d{3}\\d0\\d0\\d") || phoneNumber.matches("000\\d{4}"))
            return 100000;
        else
            return 0;
    }
}
