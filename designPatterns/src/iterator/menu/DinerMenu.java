package iterator.menu;

import iterator.menuIterator.DinerMenuIterator;
//import iterator.menuIterator.Iterator;
import java.util.Iterator;

public class DinerMenu implements Menu {
    private static final int MAX_ITEMS = 4;

    private int numberOfItems;
    private MenuItem[] menuItems;

    public DinerMenu() {
        this.menuItems = new MenuItem[MAX_ITEMS];
        this.numberOfItems = 0;

        addItem("채식주의자용 BLT", "통밀 위에(식물성) 베이컨, 상추, 토마토를 얹은 메뉴", true, 2.99);
        addItem("BTL", "통밀 위에 베이컨, 상추, 토마토를 얹은 메뉴", false, 2.99);
        addItem("오늘의 스프", "감자 샐러드를 곁들인 오늘의 스프", false, 3.29);
        addItem("핫도그", "사워크라아우트, 갖은 양념, 양파, 치즈가 곁들여진 핫도그", false, 3.05);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItems = new MenuItem(name, description, vegetarian, price);

        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("죄송합니다, 메뉴가 꽉 찼습니다, 더 이상 추가할 수 없습니다.");
        } else {
            this.menuItems[numberOfItems] = menuItems;
            numberOfItems++;
        }
    }

    public Iterator createIterator() {
        return new DinerMenuIterator(this.menuItems);
    }

    /*
    public MenuItem[] getMenuItems() {
        return this.menuItems;
    }
    */
}