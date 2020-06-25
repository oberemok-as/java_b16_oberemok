package ru.stqa.pft.sandbox;

import java.util.Date;

public class mfp {

  public static void main(String[] args) {
    hello("World");
    Date date = new Date();
    System.out.println(date);
    Square s = new Square(5);
    System.out.println("Square = " + s.area());
    Rec r = new Rec(4,6);
    System.out.println("Rec = " + r.area());
  }

  public static void hello(String user) {
    System.out.println("Hello,  " + user + "!");
  }




}