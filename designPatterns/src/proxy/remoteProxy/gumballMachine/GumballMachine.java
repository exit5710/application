package proxy.remoteProxy.gumballMachine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// rmic proxy.remoteProxy.gumballMachine.GumballMachine
// rmiregistry
public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {
    private State soldOutState;
    private State noQuarterState;
    private State hasQuarterState;
    private State soldState;
    private State winnerState;

    private State state;
    private int count;
    private String location;

    public GumballMachine(String location, int count) throws RemoteException {
        this.soldOutState = new SoldOutState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.soldState = new SoldState(this);
        this.winnerState = new WinnerState(this);
        this.count = count;
        this.location = location;

        if (count > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuartet() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");

        if (count > 0) {
            count--;
        }
    }

    public void refill(int count) {
        setCount(this.count + count);

        System.out.println("The gumball machine was just refilled; its new count is : " + this.count);

        state.refill();
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava-enabled Standing Gumball Model #2004");
        result.append("\nInventory : ").append(count).append(" gumball");

        if (count > 1) {
            result.append("s");
        }

        result.append("\n");
        result.append("Machine is ").append(state).append("\n");

        return result.toString();
    }
}