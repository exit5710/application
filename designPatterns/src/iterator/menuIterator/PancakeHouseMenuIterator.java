package iterator.menuIterator;

import iterator.menu.MenuItem;
import java.util.ArrayList;

/*
not use
 */
public class PancakeHouseMenuIterator implements Iterator {
    private ArrayList<MenuItem> menuItems;
    private int position;

    public PancakeHouseMenuIterator(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return this.position < this.menuItems.size() && this.menuItems.get(this.position) != null;
    }

    @Override
    public Object next() {
        MenuItem menuItems = this.menuItems.get(this.position);
        this.position++;

        return menuItems;
    }
}