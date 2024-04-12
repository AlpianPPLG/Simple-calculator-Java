package javadasar;

import java.util.*;

public class kalkulator{
    public static void main(String[] args){

        Scanner inputUser;
        float a,b,hasil; 
        char operator;

        inputUser = new Scanner(System.in);

        System.out.print("nilai a = ");
        a = inputUser.nextFloat();
        System.out.print("operator = ");
        operator = inputUser.next().charAt(0);
        System.out.print("nilai b = ");
        b = inputUser.nextFloat();

        if(operator != '+' && operator != '-' && operator != '*' && operator != '/') {
            System.out.println("operator tidak ditemukan");
        } else if (operator == '+'){
            hasil = a + b;
            System.out.println("hasil = " + hasil);

        } else if (operator == '-'){
            hasil = a - b;
            System.out.println("hasil = " + hasil);
        
        } else if (operator == '*'){
            hasil = a * b;
            System.out.println("hasil = " + hasil);

        } else if (operator == '/'){
            if (a == 0){
                if(b == 0) {
                    System.out.println("pembagi nol menghasilkan tak hingga");
                    hasil = Float.POSITIVE_INFINITY;
                } else {
                    System.out.println("Tidak dapat dibagi dengan nol");
                    hasil = Float.NaN;
                }
                System.out.println("hasil = " + hasil);
            } else {
                hasil = a / b;
                System.out.println("hasil = " + hasil);
            }
        } else {
            System.out.println("operator tidak ditemukan");
        }
    }
}