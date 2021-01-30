package command.Tv;

import command.command.Command;

public class TvOnCommand implements Command {
    Tv tv;

    public TvOnCommand(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
        tv.setInput("DVD");
    }

    @Override
    public void undo() {
        tv.off();
    }
}