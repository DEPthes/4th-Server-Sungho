//추상 클래스 선언
abstract class Animal{
    private String name;

    public Animal(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //추상 메소드
    public abstract void makeSound();
}

//추상 클래스를 상속받는 Dog 클래스(실체 클래스)
class Dog extends Animal {
    public Dog(String name){
        super(name);
    }

    //추상메소드 구현
    @Override
    public void makeSound() {
        System.out.println("멍멍!");
    }
}

//추상 클래스를 상속받는 Cat 클래스(실체 클래스)
class Cat extends Animal {
    public Cat(String name){
        super(name);
    }

    //추상 메소드 구현
    @Override
    public void makeSound() {
        System.out.println("야옹!");
    }
}

public class AbstractClassMethod {
    public static void main(String[] args) {
        Dog dog = new Dog("강아지");
        Cat cat = new Cat("고양이");

        System.out.println(dog.getName());
        dog.makeSound();
        System.out.println(cat.getName());
        cat.makeSound();
    }

}
