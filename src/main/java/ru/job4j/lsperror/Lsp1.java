package ru.job4j.lsperror;

public class Lsp1 {
    public static Rectangle triangle() {
        return new Square();
    }
    
    public static void main(String[] args) {
        Rectangle rectangle = triangle();
        rectangle.height = 5;
        rectangle.width = 10;

        System.out.println(rectangle.area());
    }
}

class Rectangle {
    public int height;
    public int width;

    public int area() {
        return width * height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

    class Square extends Rectangle {

        @Override
        public void setHeight(int height) {
            this.height = height;
           width = height;
        }

        @Override
        public void setWidth(int width) {
            this.width = width;
          height = width;
        }

        @Override
        public int area() {
            return width * width;
        }
    }
