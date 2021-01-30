package iterator.menu;

import java.util.ArrayList;

public class MenuTestDrive {
    public static void main(String[] args) {
        Menu pancakeHouseMenu = new PancakeHouseMenu();
        Menu dinerMenu = new DinerMenu();
        Menu cafeMenu = new CafeMenu();

        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(pancakeHouseMenu);
        menus.add(dinerMenu);
        menus.add(cafeMenu);

        Waitress waitress = new Waitress(menus);
        waitress.printMenu();

        /*
        ArrayList<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
        MenuItem[] lunchItems = dinerMenus.getMenuItems();

        System.out.println("------------------------------------------ breakfastItems ------------------------------------------");
        for (MenuItem menuItem : breakfastItems) {
            System.out.print(menuItem.getName() + " ");
            System.out.print(menuItem.getDescription() + " ");
            System.out.print(menuItem.isVegetarian() + " ");
            System.out.println(menuItem.getPrice());
        }

        System.out.println("\n------------------------------------------ lunchItems ------------------------------------------");
        for (MenuItem menuItem : lunchItems) {
            System.out.print(menuItem.getName() + " ");
            System.out.print(menuItem.getDescription() + " ");
            System.out.print(menuItem.isVegetarian() + " ");
            System.out.println(menuItem.getPrice());
        }
        */
    }
}