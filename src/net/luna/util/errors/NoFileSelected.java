package net.luna.util.errors;

import net.luna.modules.Logger;

public class NoFileSelected extends Exception {
    public NoFileSelected() {
        super("No file was selected please do so with \'FileHandler.getFileHandler().setFile(${fileName})\'");
        Logger.getLogger()
                .ERROR("No file was selected please do so with \'FileHandler.getFileHandler().setFile(${fileName})\'",
                        this);
    }
}
