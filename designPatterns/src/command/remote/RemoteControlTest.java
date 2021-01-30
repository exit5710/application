package command.remote;

import command.door.GarageDoor;
import command.door.GarageDoorOpenCommand;
import command.light.Light;
import command.light.LightOnCommand;

public class RemoteControlTest {
    public static void main(String[] args) {
        LightOnCommand lightOn = new LightOnCommand(new Light());
        GarageDoorOpenCommand doorOpen = new GarageDoorOpenCommand(new GarageDoor());

        SimpleRemoteControl remote = new SimpleRemoteControl();
        remote.setCommand(lightOn);
        remote.buttonWasPressed();
        remote.setCommand(doorOpen);
        remote.buttonWasPressed();
    }
}