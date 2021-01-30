package templateMethod.duck;

public class Duck implements Comparable<Duck> {
    private String name;
    private int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(Duck duck) {
        // getWeight() 크면 1, 같으면 0, duck.getWeight() 크면 -1
        return Integer.compare(getWeight(), duck.getWeight());
    }

    @Override
    public String toString() {
        return getName() + " weights : " + getWeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}