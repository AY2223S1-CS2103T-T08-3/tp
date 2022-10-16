package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t08-3.github.io/tp/UserGuide.html";
    public static final String DESCRIPTION = "uNivUSal is a desktop app for managing contacts catered to CS2103T "
            + "students, TAs, and professors, optimized for use via a Command Line  \n"
            + "Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type "
            + "fast, uNivUSal can get your contact management \n"
            + "tasks done faster than traditional GUI apps.\n\n"
            + "Here are some of the most commonly used commands!";
    public static final String HELP_MESSAGE_TITLE = "Refer to the user guide for more info: " + USERGUIDE_URL
            + "\n\n" + DESCRIPTION;

    public static final String TABLE_HEADERS = " |  Command  |                     Format                      "
            + "|                    Example                    | ";
    public static final String ADD_EXAMPLE = " |   help    | add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]       "
            + "| add n/John Doe p/98765432 e/johnd@example.com |";
    public static final String LIST_EXAMPLE = " |   list    | list                                            "
            + "| list                                          | ";
    public static final String EDIT_EXAMPLE = " |   edit    | edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG] "
            + "| edit 1 p/91234567 e/janedoe@example.com       |";
    public static final String DELETE_EXAMPLE = " |   delete  | delete INDEX                                    "
            + "| find Betsy -> delete 1                        | ";
    public static final String CLEAR_EXAMPLE = " |   clear   | clear                                           "
            + "| clear                                         |";
    public static final String EXIT_EXAMPLE = " |   exit    | exit                                            "
            + "| exit                                          |";

    public static final String HELP_MESSAGE_BODY = TABLE_HEADERS + "\n"
            + ADD_EXAMPLE + "\n"
            + LIST_EXAMPLE + "\n"
            + EDIT_EXAMPLE + "\n"
            + DELETE_EXAMPLE + "\n"
            + CLEAR_EXAMPLE + "\n"
            + EXIT_EXAMPLE + "\n";


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessageTitle;

    @FXML
    private Label helpMessageBody;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessageTitle.setText(HELP_MESSAGE_TITLE);
        helpMessageBody.setText(HELP_MESSAGE_BODY);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
