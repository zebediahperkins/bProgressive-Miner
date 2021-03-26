package scripts.gui;

public interface GUI {
    /**
     *
     * @return The Controller associated with this GUI
     */
    Controller getController();

    /**
     *
     * @return Whether the GUI was closed with the close() method
     */
    boolean show();

    /**
     * Called when the GUI is closed naturally
     */
    void close();

    /**
     * Called when the GUI is closed by force
     */
    void forceClose();

    /**
     *
     * @return Whether this GUI is open
     */
    boolean isOpen();
}
