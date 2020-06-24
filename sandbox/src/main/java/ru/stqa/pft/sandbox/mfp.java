package ru.stqa.pft.sandbox;

import java.util.Date;

public class mfp {

  public static void main(String[] args) {
    hello("World");
    Date date = new Date();
    System.out.println(date);
    double len = 5;
    System.out.println("Square = " + area(len));
    double a=2;double b=3;
    System.out.println("Square = " + area(a,b));
  }

  public static void hello(String user) {
    System.out.println("Hello,  " + user + "!");
  }

  public static double area(double l){
    return l*l;
  }

  public static double area(double a,double b){
    return a*b;  }

}