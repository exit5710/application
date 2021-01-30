package iterator.menu;

import java.util.ArrayList;
//import iterator.menuIterator.Iterator;
import java.util.Iterator;

public class Waitress {
    /*
    private PancakeHouseMenu pancakeHouseMenu;
    private DinerMenu dinerMenu;
    private Menu pancakeHouseMenu;
    private Menu dinerMenu;
    private Menu cafeMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
        this.cafeMenu = cafeMenu;
    }
    */
    private ArrayList<Menu> menus;

    public Waitress(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public void printMenu() {
        for (Menu menu : this.menus) {
            printMenu(menu.createIterator());
        }
        /*
        Iterator<Menu> iterator = this.menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = (Menu) iterator.next();
            printMenu(menu.createIterator());
        }
        */
        /*
        Iterator pancakeIterator = this.pancakeHouseMenu.createIterator();
        Iterator dinerIterator = this.dinerMenu.createIterator();
        Iterator cafeIterator = this.cafeMenu.createIterator();

        System.out.println("\n------------------ 아침 메뉴 ----------------------------------------------------------------");
        this.printMenu(pancakeIterator);

        System.out.println("\n------------------ 점심 메뉴 ----------------------------------------------------------------");
        this.printMenu(dinerIterator);

        System.out.println("\n------------------ 저녁 메뉴 ----------------------------------------------------------------");
        this.printMenu(cafeIterator);
        */
    }

    private void printMenu(Iterator iterator) {
        System.out.println();
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();

            System.out.print(menuItem.getName() + " / ");
            System.out.print(menuItem.getDescription() + " / ");
            System.out.print(menuItem.isVegetarian() + " / ");
            System.out.println(menuItem.getPrice());
        }
    }
}