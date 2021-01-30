package command.remote;

import command.Tv.Tv;
import command.Tv.TvOffCommand;
import command.Tv.TvOnCommand;
import command.command.Command;
import command.light.Light;
import command.light.LightOffCommand;
import command.light.LightOnCommand;
import command.macro.MacroCommand;
import command.stereo.Stereo;
import command.stereo.StereoOffCommand;
import command.stereo.StereoOnWithCdCommand;

public class RemoteMacro {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light("Living Room");
        Stereo stereo = new Stereo("Living Room");
        Tv tv = new Tv("Living Room");

        LightOnCommand lightOnCommand = new LightOnCommand(light);
        StereoOnWithCdCommand stereoOnWithCdCommand = new StereoOnWithCdCommand(stereo);
        TvOnCommand tvOnCommand = new TvOnCommand(tv);

        LightOffCommand lightOffCommand = new LightOffCommand(light);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);
        TvOffCommand tvOffCommand = new TvOffCommand(tv);

        Command[] partyOn = {lightOnCommand, stereoOnWithCdCommand, tvOnCommand};
        Command[] partyOff = {lightOffCommand, stereoOffCommand, tvOffCommand};

        MacroCommand partyOnMode = new MacroCommand(partyOn);
        MacroCommand partyOffMode = new MacroCommand(partyOff);

        remoteControl.setCommand(0, partyOnMode, partyOffMode);

        System.out.println(remoteControl);
        System.out.println("----- Pushing Macro On -----");
        remoteControl.onButtonWasPushed(0);
        System.out.println("\n ----- Pushing Macro Off -----");
        remoteControl.offButtonWasPushed(0);
    }
}
