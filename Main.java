
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final ArrayList<String> romanNumbers = new ArrayList<>(Arrays.asList("O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"));
    private static final ArrayList<String> sign = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение в формате: 5 + 4, V * IV");
        System.out.print(calcun(sc));
    }
    public static String calcun(Scanner sc) throws Exception {
        String st = sc.nextLine();
        String[] parts = st.split(" ");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        char op = part2.charAt(0);
        if (parts.length != 3) {
            throw new Exception("Не правильно введено выражение");
        }
        if (!sign.contains(parts[1])) {
            throw new Exception("Не правильно введен знак");
        }
        if (isArabic(parts[0], parts[2])) {
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[2]);
            return String.valueOf(resultArabian(part1,part2,part3));
        } else if (isRomanian(parts[0], parts[2])) {
            Integer result = getRezultRoman(part1,part3,op);
            if (result < 1) {
                throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
            }
            String resultRoman = convertNumToRoman(result);
            return resultRoman;
        } else {
            throw new Exception("Вы ввели числа из разных систем изчисления");
        }
    }
    private static boolean isRomanian(String part1, String part3) throws Exception {
        int index1 = romanNumbers.indexOf(part1); // в случае если в списке нет элемента indexOf возвращает -1
        int index2 = romanNumbers.indexOf(part3);
        if (index1 > 10 || index2 > 10) {
            throw new Exception("Введены не подходящие числа");
        }
        if ((index1 > 0) && (index2 > 0)) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean isArabic(String op1, String op2) throws Exception {
        try {
            int a = Integer.parseInt(op1);
            int b = Integer.parseInt(op2);

            if ((a > 0 && a <= 10) && (b > 0 && b <= 10)) {
                return true;
            } else {
                throw new Exception("Введены не подходящие числа");
            }
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    private static int resultArabian(String part1, String part2, String part3){
        int a = Integer.parseInt(part1);
        char op = part2.charAt(0);
        int b = Integer.parseInt(part3);

        return getRezult(a,b,op);
    }
    private static int getRezult(int a, int b,char op){
        switch (op){
            case '+' :
                return a + b;
            case '-' :
                return a - b;
            case '*' :
                return a * b;
            case '/' :
                return a / b;
            default: throw new IllegalArgumentException("Неверное значение");
        }
    }
    private static int getRoman(String part) {
        switch (part) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return -1;
        }
    }
    private static int getRezultRoman(String num1, String num2, char op) {
        int part1 = getRoman(num1);
        int part2 = getRoman(num2);
        switch (op) {
            case '+':
                return part1 + part2;
            case '-':
                return part1 - part2;
            case '*':
                return part1 * part2;
            case '/':
                return part1 / part2;
            default:
                throw new IllegalArgumentException("Неверное значение");
        }
    }
    private static String convertNumToRoman(int result) {
        final String s = romanNumbers.get(result);
        return s;
    }
}