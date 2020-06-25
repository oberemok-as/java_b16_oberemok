package ru.stqa.pft.sandbox;

public class Rec {
  public double a;
  public double b;

  public Rec(double a, double b) {
    this.a = a;
    this.b = b;
  }


  public double area() {
    return this.a * this.b;
  }
}
