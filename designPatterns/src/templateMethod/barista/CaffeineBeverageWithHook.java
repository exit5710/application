package templateMethod.barista;

public abstract class CaffeineBeverageWithHook {
    protected final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();

        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    protected boolean customerWantsCondiments() {
        return true;
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