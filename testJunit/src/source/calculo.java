package source;

import java.util.Scanner;

public class calculo {

     int result;


    public int comprobarCalculo(int valorA, String signo, int valorB){

        switch (signo) {
            case "+" :
                result = valorA + valorB;
            break;

            case "-" :
                result = valorA - valorB;
            break;

            case "/":
                result = valorA / valorB;
            break;

            case "*":
                result = valorA * valorB;
            break;

            default:
                System.out.println("ERROR");
        }

        System.out.println(result);
        return result;

    }
}
