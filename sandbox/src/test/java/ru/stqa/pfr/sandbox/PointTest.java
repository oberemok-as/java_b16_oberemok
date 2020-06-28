package ru.stqa.pfr.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest {
  @Test
  public void testArea(){
    Point p1=new Point(1,1);
    Point p2=new Point(2,2);
    Assert.assertEquals(Point.distance(p1,p2),1.4142135623730951);
  }
}
