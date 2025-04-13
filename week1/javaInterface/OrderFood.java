package week1.javaInterface;

// 식당 손님
class Customer {
    public String customer;

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}

// ResCustomer 클래스로부터 단골손님 A와 B 상속
class CustomerA extends Customer {
}

class CustomerB extends Customer {
}

// 식당 사장
class Owner {
    public void giveFood(CustomerA customerA) {
        System.out.println("손님 A에게 김치찌개를 준다");
    }

    public void giveFood(CustomerB customerB) {
        System.out.println("손님 B에게 제육볶음을 준다");
    }
}

// 음식 주문
public class OrderFood {
    public static void main(String[] args) {
        Owner owner = new Owner();
        CustomerA a = new CustomerA();
        CustomerB b = new CustomerB();

        owner.giveFood(a);
        owner.giveFood(b);
    }
}
