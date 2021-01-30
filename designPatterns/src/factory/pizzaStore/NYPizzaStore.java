package factory.pizzaStore;

import factory.pizza.NYStyleCheesePizza;
import factory.pizza.Pizza;

public class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new NYStyleCheesePizza();
        }

        return null;
    }
}
