import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Integer MAX_SIMLE_VALUE = 12;
    public static Integer COMPLEX_VALUE = 12;

    public static void main(String[] args) {
        Integer n = COMPLEX_VALUE;
        System.out.println("default number == " + n);

        getFactorial(n);

//        //FOR TEST
//        List<Integer> num1 = new ArrayList<>();
//        num1.add(3);
//        num1.add(1);
//
//        System.out.println(num1);
//        List<Integer> num2 = new ArrayList<>();
//        num2.add(0);
//        num2.add(0);
//        num2.add(6);
//        num2.add(1);
//        num2.add(0);
//        num2.add(0);
//        num2.add(9);
//        num2.add(7);
//        num2.add(4);
//
//
//        System.out.println(num2);
//        System.out.println("===========================================================");
//        System.out.println();
//
//        CustomBigInteger customBigInteger = new CustomBigInteger();
//        customBigInteger.setParsedNumber(num2);
//        System.out.println("result: " + mult(num2, num1));
    }

    private static List<Integer> mult(List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>();
        Integer rest = 0;
        int range = 0;

        for (Integer eltOfFirst : first) {
            List<Integer> innerResults = new ArrayList<>();
            for (Integer eltOfSecond : second) {
                Integer localResult = eltOfFirst * eltOfSecond;
                innerResults.add((localResult + rest) % 10);
                rest = (localResult + rest) / 10;
            }
            if (rest != 0) {
                innerResults.add(rest);
            }

            List<Integer> innerResultsWithShift = addShift(innerResults, range);

            result = sum(result, innerResultsWithShift);

            range++;
            rest = 0;
        }

        return result;
    }

    private static List<Integer> addShift(List<Integer> digits, int range) {
        for (int i = 0; i < range; i++) {
            digits.add(0, 0);
        }
        return digits;
    }

    private static List<Integer> sum(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        Integer rest = 0;

        List<Integer> bigger = right;
        List<Integer> lower = left;
        if (left.size() > right.size()) {
            bigger = left;
            lower = right;
        }

        for (int i = 0; i < bigger.size(); i++) {
            Integer localResult = bigger.get(i) + ((lower.size() - 1 < i) ? 0 : lower.get(i)) + rest;
            rest = localResult / 10;
            result.add(localResult % 10);
        }
        if (rest != 0) {
            result.add(rest);
        }

        return result;
    }

    private static CustomBigInteger getFactorial(int n) {
        CustomBigInteger result = intToCustomBigInteger(1);

        for (int i = 1; i <= n; i++) {
            result.setParsedNumber(mult(result.getDigits(), intToCustomBigInteger(i).getDigits()));
            System.out.println("Factorial of " + i + " == " + customBigIntegerToString(result));
        }

        return result;
    }

    public static CustomBigInteger intToCustomBigInteger(int intNumber) {
        CustomBigInteger customBigInteger = new CustomBigInteger();
        List<Integer> parsedNumber = new ArrayList<>();

        while (intNumber / 10 > 0) {
            parsedNumber.add(intNumber % 10);
            intNumber /= 10;
        }
        parsedNumber.add(intNumber % 10);

        customBigInteger.setParsedNumber(parsedNumber);

        return customBigInteger;
    }

    public static Integer customBigIntegerToInt(CustomBigInteger customBigInteger) { // UP TO 12!
        List<Integer> digits = customBigInteger.getDigits();
        Integer result = 0;

        for (int i = 0; i < digits.size(); i++) {
            Integer digit = digits.get(i);
            for (int powerOfTen = 0; powerOfTen < i; powerOfTen++) {
                digit *= 10;
            }
            result += digit;
        }

        return result;
    }

    public static String customBigIntegerToString(CustomBigInteger customBigInteger) {
        List<Integer> digits = customBigInteger.getDigits();
        String result = "";

        for (int i = digits.size() - 1; i >= 0 ; i--) {
            Integer digit = digits.get(i);
            result = result + digit.toString();
        }

        return result;
    }
}
