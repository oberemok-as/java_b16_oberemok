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
    //Задание 1
    //передача параметров
    Point p1 = new Point(1,1);
    Point p2 = new Point(2,2);
    System.out.println("Расстояние между точками = "+ Point.distance(p1,p2));
  }

  public static void hello(String user) {
    System.out.println("Hello,  " + user + "!");
  }




}