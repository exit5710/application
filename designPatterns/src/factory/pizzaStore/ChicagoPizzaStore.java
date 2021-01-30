package factory.pizzaStore;

import factory.pizza.ChicagoStyleCheesePizza;
import factory.pizza.Pizza;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }

        return null;
    }
}
