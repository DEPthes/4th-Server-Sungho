package javaInterface;

interface ICustomer {
    String getOrder();
}

// 단골손님 A와 B 클래스에서 인터페이스 Customer의 getOrder 추상 메서드 구현
class ICustomerA implements ICustomer {
    public String getOrder() {
        return "손님 A에게 김치찌개를 준다";
    }
}

class ICustomerB implements ICustomer {
    public String getOrder() {
        return "손님 B에게 제육볶음을 준다";
    }
}

// 식당 사장
class IOwner {
    public void giveFood(ICustomer iCustomer) {
        System.out.println(iCustomer.getOrder());
    }
}

// 음식 주문
public class InterfaceOrderFood {
    public static void main(String[] args) {
        IOwner iOwner = new IOwner();
        ICustomerA a = new ICustomerA();
        ICustomerB b = new ICustomerB();

        iOwner.giveFood(a);
        iOwner.giveFood(b);
    }
}