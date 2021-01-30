package templateMethod.barista;

public abstract class CaffeineBeverage {
    protected final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    protected abstract void addCondiments();

    protected void pourInCup() {
        System.out.println("pour in cup");
    }

    protected abstract void brew();

    protected void boilWater() {
        System.out.println("boil Water");
    }
}