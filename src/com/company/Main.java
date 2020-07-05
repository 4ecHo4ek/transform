package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите основание исходной системы счисления");
        int a = getFoot();
        System.out.println("Введите основание целевой системы счисления");
        int b = getFoot();
        //тут считываем число
        System.out.println("Введите число, записанное в а-ричной системе счисления");
        String x = getNum(a);
        System.out.println("Число " + x + " в " + a + "-ричной системе счисления ");
        String answer = getAnswer(a , b, x);
        System.out.println("равняется " + answer + " в " + b + "-ричной системе счисления");

    }

    static int getFoot() {

        boolean good;
        do {
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int foot = scan.nextInt();
                if (foot >= 2 && foot <= 36) {
                    return foot;
                }
                System.out.println("Основание больше 36 или меньше 2");
            } else {
                System.out.println("Введите правильное основание");
            }
            good = false;
        } while (!good);
        return 0;
    }

    static String getNum(int a) {
        boolean good;
        do {
            Scanner scan = new Scanner(System.in);
            HashMap<String, String> map = mapForCalc(a);

            if (scan.hasNextLine()) {
                boolean flag = true;
                String num = scan.nextLine();
                for (int i = 0; i < num.length(); i++) {
                    String numChar = String.valueOf(num.charAt(i));
                    if (!map.containsValue(numChar)) {
                        flag = false;
                        break;
                    }
                }
               if (flag) {
                   return num;
               }
            } else {
                System.out.println("Введите правильное число");
            }
            good = false;
        } while (!good);
        return "0";
    }

    static String getAnswer(int a, int b, String x) {
        StringBuilder answer = new StringBuilder();

        HashMap<String, String> map;
        if (a == b) {
            answer = new StringBuilder(x);
        } else {
            map = mapForCalc(b);
            int transformTo10 = transformFromATo10(x, a);

            do {
                answer.insert(0, map.get(String.valueOf(transformTo10 % b)));
                transformTo10 /= b;
            } while (transformTo10 > 0);


        }

        return answer.toString();
    }

    static HashMap<String, String> mapForCalc( int b) {
        HashMap<String, String> map = new HashMap<>();
        String [] arrayForEntering = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s",
                "t","u","v","w","x","y","z"};

        if (b < 10) {
            for (int i = 0; i < b; i++) {
                map.put(String.valueOf(i), String.valueOf(i));
            }
        } else {
            for (int i = 0; i < 9; i++) {
                map.put(String.valueOf(i), String.valueOf(i));
            }
            for (int i = 0; i < b - 10; i++) {
                map.put(String.valueOf(i + 10), arrayForEntering[i]);
            }
        }

        return map;
    }

    static int transformFromATo10(String x, int a) {
        int transform = 0;

        HashMap<String, String> map = new HashMap<>();
        String [] arrayForEntering = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s",
                "t","u","v","w","x","y","z"};

        if (a < 10) {
            for (int i = 0; i < a; i++) {
                map.put(String.valueOf(i), String.valueOf(i));
            }
        } else {
            for (int i = 0; i < 9; i++) {
                map.put(String.valueOf(i), String.valueOf(i));
            }
            for (int i = 0; i < a - 10; i++) {
                map.put(arrayForEntering[i], String.valueOf(i + 10));
            }
        }

        for (int i = 0; i < x.length(); i++) {
            String numChar = String.valueOf(x.charAt(i));
            int mult = Integer.parseInt(map.get(numChar));
            transform += mult * Math.pow(10.0, (x.length() - 1) - i);
        }

        return transform;
    }



}
