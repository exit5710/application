package composite.menu;

import java.util.ArrayList;

public class Menu extends MenuComponent {
    private ArrayList<MenuComponent> menuComponents;
    private String name;
    private String description;

    public Menu(String name, String description) {
        this.menuComponents = new ArrayList<>();
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        this.menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        this.menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return this.menuComponents.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        System.out.print("\n" + getName());
        System.out.println(",   " + getDescription());
        System.out.println("-------------------------------------------------------------------------");

        for (MenuComponent menuComponent : this.menuComponents) {
            menuComponent.print();
        }

        /*
        Iterator<MenuComponent> iterator = this.menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();
        }
        */
    }
}