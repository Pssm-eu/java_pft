package ru.stqa.pft.sandbox;

public class helloworld {

  public static void main(String[] args) {
    shrek("Somebody");
    shrek("Shrek");
    shrek("Donkey");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }
  public static void shrek(String somebody) {
    System.out.println(somebody + " once told me");
  }

}