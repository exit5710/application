package iterator.menuIterator;

import iterator.menu.MenuItem;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator {
    private MenuItem[] menuItems;
    private int position;

    public DinerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return this.position < this.menuItems.length && this.menuItems[this.position] != null;
    }

    @Override
    public Object next() {
        MenuItem menuItems = this.menuItems[this.position];
        this.position++;

        return menuItems;
    }

    @Override
    public void remove() {
    }
}