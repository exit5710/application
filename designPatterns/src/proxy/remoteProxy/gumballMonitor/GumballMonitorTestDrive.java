package proxy.remoteProxy.gumballMonitor;

import proxy.remoteProxy.gumballMachine.GumballMachineRemote;

import java.rmi.Naming;

// java proxy.remoteProxy.gumballMonitor.GumballMonitorTestDrive seoul(args)
public class GumballMonitorTestDrive {
    public static void main(String[] args) {
        String[] location = {"rmi://localhost/" + args[0]};

        GumballMonitor[] monitor = new GumballMonitor[location.length];

        for (int i = 0; i < location.length; i++) {
            try {
                GumballMachineRemote machine = (GumballMachineRemote) Naming.lookup(location[i]);
                monitor[i] = new GumballMonitor(machine);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (GumballMonitor gumballMonitor : monitor) {
            gumballMonitor.report();
        }
    }
}