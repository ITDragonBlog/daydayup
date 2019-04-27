package com.kotlin.demo;

import java.util.ArrayList;
import java.util.List;

public class JavaDemo {
    public static void main(String[] args) {
        int i = 5;
        String print = "";
        switch(i){
            case 0:  print += "0";
            case 1:  print += "1";
            case 2:  print += "2";
            case 3:  print += "3";
            default: print += "default";
        }
        System.out.println(print);
    }
}
