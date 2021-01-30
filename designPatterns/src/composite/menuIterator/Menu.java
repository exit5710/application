package composite.menuIterator;

import java.util.ArrayList;
import java.util.Iterator;

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
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new CompositeIterator(this.menuComponents.iterator());
    }
}