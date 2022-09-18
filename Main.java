import java.util.Scanner;
class Main {
//    rim raqamlarini arab raqamlariga o`tkazish uchun
    public static int value(char r)  {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

//    rim raqam => arab raqami
    public static int romanToDecimal(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
//            belgilarni bitta-bitta olib beradi
            int s1 = value(str.charAt(i));
            if (i + 1 < str.length()) {
                int s2 = value(str.charAt(i + 1));
                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
            }
        }
        return res;
    }
    public static String integerToRoman1(int number) {
        StringBuilder s = new StringBuilder();
//        append qo`shib beradi
        while (number >= 1000) {
            s.append("M");
            number -= 1000;
        }
        while (number >= 900) {
            s.append("CM");
            number -= 900;
        }
        while (number >= 500) {
            s.append("D");
            number -= 500;
        }
        while (number >= 400) {
            s.append("CD");
            number -= 400;
        }
        while (number >= 100) {
            s.append("C");
            number -= 100;
        }
        while (number >= 90) {
            s.append("XC");
            number -= 90;
        }
        while (number >= 50) {
            s.append("L");
            number -= 50;
        }
        while (number >= 40) {
            s.append("XL");
            number -= 40;
        }
        while (number >= 10) {
            s.append("X");
            number -= 10;
        }
        while (number >= 9) {
            s.append("IX");
            number -= 9;
        }
        while (number >= 5) {
            s.append("V");
            number -= 5;
        }
        while (number >= 4) {
            s.append("IV");
            number -= 4;
        }
        while (number >= 1) {
            s.append("I");
            number -= 1;
        }
        return s.toString();
    }
//    matn rim raqamlarini tekshiiradi
    public static boolean isRoman(String s) {
        return !s.isEmpty()
                && s.matches("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
    }
//    arab raqamini tekshiradi
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
//    stringni o`qiydi
    public static boolean isCorrect(String[] arr) {
        if(arr.length > 3) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return false;
        } else if(arr.length < 3){
            System.out.println("throws Exception //т.к. строка не является математической операцией");
            return false;
        }

        if(!isNumeric(arr[0]) && !isRoman(arr[0]))
            return false;
        if(!isNumeric(arr[2]) && !isRoman(arr[2]))
            return false;
        if("+-*/".indexOf(arr[1]) == -1)
            return false;
        return true;
    }
    public static void calculateRom(String[] arr) {
        int a = romanToDecimal(arr[0]);
        int b = romanToDecimal(arr[2]);
        int res = -1;
        switch(arr[1]) {
            case "+":
                res = a+b;
                break;
            case "-":
                res = a-b;
                break;
            case "*":
                res = a*b;
                break;
            case "/":
                res = a/b;
                break;
        }
        if(res <= 0){
            System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
            System.exit(0);
        }
        System.out.println(integerToRoman1(res));
    }
    public static void calculateArab(String[] arr) {
//        parseInt String => int
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[2]);
        switch(arr[1]) {
            case "+":
                System.out.println(a+b);
                break;
            case "-":
                System.out.println(a-b);
                break;
            case "*":
                System.out.println(a*b);
                break;
            case "/":
                System.out.println(a/b);
                break;
        }
    }
    public static void main(String[] args) {
//
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Misolni kiriting:");
            String str = input.nextLine();
//            split stringni bo`lib beradi
            String[] arr = str.split(" ");
            if(isCorrect(arr)) {
                if(isNumeric(arr[0]) && isNumeric(arr[2]))
                    calculateArab(arr);
                else if (isRoman(arr[0]) && isRoman(arr[2]))
                    calculateRom(arr);
                else
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            }
        }

    }
}