package proxy.remoteProxy.gumballMachine;

import java.rmi.Naming;

// java proxy.remoteProxy.gumballMachine.GumballMachineTestDrive seoul(args) 72(args)
public class GumballMachineTestDrive {
    public static void main(String[] args) {
        int count;

        if (args.length < 2) {
            System.out.println("GumballMachine <name> <inventory>");
            System.exit(1);
        }

        try {
            count = Integer.parseInt(args[1]);
            GumballMachine gumballMachine = new GumballMachine(args[0], count);
            Naming.bind(args[0], gumballMachine);
            System.out.println(args[0].toString() + " gumball machine information");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}