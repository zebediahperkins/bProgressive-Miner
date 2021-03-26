package scripts.gui;

public interface Controller {
    /**
     * Called after initialize function
     */
    void delayed();

    /**
     * Called on forced close
     */
    void onClose();

    void setGui(GUI gui);

    /**
     *
     * @return The GUI associated with this Controller
     */
    GUI getGui();

    void setParentGui(GUI parentGui);

    /**
     *
     * @return The GUI that spawned the GUI associated with this Controller
     */
    GUI getParentGui();
}
