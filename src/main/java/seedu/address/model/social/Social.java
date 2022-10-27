package seedu.address.model.social;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.OpenCommand.MESSAGE_BAD_LINK;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import seedu.address.model.social.exceptions.SocialException;

/**
 * Represents a Social in uNivUSal.
 */
public class Social {

    enum Socials {
        WHATSAPP,
        TELEGRAM,
        EMAIL,
        INSTAGRAM
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Socials can take any values, and it should not be blank";

    /*
     * The first character of the social link must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final String SCHEME = "http://";

    private static final String SCHEMES = "https://";

    //links array holds the links for the socials of the person.
    //The index of the links in the array corresponds with the enum declared.
    //Example: links[0] should hold the link to the Whatsapp social media account.
    private String[] links;

    private Socials preferred;

    private String imageUrl;


    /**
     * Constructs a {@code Social}.
     */
    public Social() {
        this.links = new String[5];
        links[0] = "null";
        links[1] = "null";
        links[2] = "null";
        links[3] = "null";
        links[4] = "file:src/main/resources/images/profile_pic.png";
    }

    /**
     * Returns true if a given string is a valid URL link.
     */
    public static boolean isValidSocial(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Adds the Whatsapp link of the person into the links array.
     * @param link
     */
    public void addWhatsapp(String link) {
        requireNonNull(link);
        links[0] = link;
    }

    /**
     * Adds the Telegram link of the person into the links array.
     * @param link
     */
    public void addTelegram(String link) {
        requireNonNull(link);
        links[1] = link;
    }

    /**
     * Adds the Email link of the person into the links array.
     * @param link
     */
    public void addEmail(String link) {
        requireNonNull(link);
        links[2] = link;
    }

    /**
     * Adds the Instagram link of the person into the links array.
     * @param link
     */
    public void addInstagram(String link) {
        requireNonNull(link);
        links[3] = link;
    }

    /**
     * Sets the Whatsapp link to null
     */
    public void deleteWhatsapp() {
        links[0] = null;
    }

    /**
     * Sets the Telegram link to null
     */
    public void deleteTelegram() {
        links[1] = null;
    }

    /**
     * Sets the Email link to null
     */
    public void deleteEmail() {
        links[2] = null;
    }

    /**
     * Sets the Instagram link to null
     */
    public void deleteInstagram() {
        links[3] = null;
    }

    /**
     * Returns Whatsapp link of the person.
     *
     * @return Whatsapp link of the person.
     */
    public String getWhatsapp() {
        return links[0].trim();
    }

    public void addUrl(String imageUrl) {
        links[4] = imageUrl;
    }
    /**
     * Returns ImageURL of the person, defaults to default picture if there is none.
     *
     * @return ImageURL of the person.
     */
    public String getImageUrl() {
        return links[4].trim();
    }

    /**
     * Returns Telegram link of the person.
     *
     * @return Telegram link of the person.
     */
    public String getTelegram() {
        return links[1];
    }

    /**
     * Returns Email link of the person.
     *
     * @return Email link of the person.
     */
    public String getEmail() {
        return links[2];
    }

    /**
     * Returns Instagram link of the person.
     *
     * @return Instagram link of the person.
     */
    public String getInstagram() {
        return links[3];
    }

    /**
     * Sets input to be the preferred Socials for communication.
     * @param input preferred Socials for communication.
     */
    public void prefer(String input) {
        for (Socials s : Socials.values()) {
            if (input.equalsIgnoreCase(s.toString())) {
                this.preferred = s;
            }
        }
    }

    /**
     * Returns the preferred Socials for communication.
     * @return preferred Socials for communication.
     */
    public String getPreferred() {
        if (this.preferred == null) {
            return "NO PREFERENCE SET";
        } else {
            return this.preferred.toString();
        }
    }

    public String getPreferredLink() {
        if (this.preferred == null) {
            return null;
        } else {
            switch (this.preferred) {
            case WHATSAPP:
                return this.getWhatsapp();

            case TELEGRAM:
                return this.getTelegram();

            case EMAIL:
                return this.getEmail();

            case INSTAGRAM:
                return this.getInstagram();

            default:
                return null;
            }

        }
    }
    /**
     * Opens the link inside Whatsapp
     */
    public void openWhatsapp() throws SocialException {
        try {
            if (getWhatsapp().equals(null) || getWhatsapp().equals("null")) {
                throw new SocialException("No Whatsapp Link");
            } else if (getWhatsapp().startsWith(SCHEME) || getWhatsapp().startsWith(SCHEMES)) {
                URI uri = new URI(getWhatsapp());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            } else {
                URI uri = new URI(SCHEME + getWhatsapp());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            }
        } catch (URISyntaxException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (IOException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (SocialException e) {
            throw new SocialException("No Whatsapp Link");
        }
    }

    /**
     * Opens the link inside Telegram
     */
    public void openTelegram() throws SocialException {
        try {
            if (getTelegram().equals(null) || getTelegram().equals("null")) {
                throw new SocialException("No Telegram Link");
            }
            if (getTelegram().startsWith(SCHEME) || getTelegram().startsWith(SCHEMES)) {
                URI uri = new URI(getTelegram());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            } else {
                URI uri = new URI(SCHEME + getTelegram());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            }
        } catch (URISyntaxException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (IOException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (SocialException e) {
            throw new SocialException("No Telegram Link");
        }
    }

    /**
     * Opens the link inside Email
     */
    public void openEmail() throws SocialException {
        try {
            if (getEmail().equals(null) || getEmail().equals("null")) {
                throw new SocialException("No Email Link");
            }
            URI uri = new URI(getEmail());
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (IOException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (SocialException e) {
            throw new SocialException("No Email Link");
        }
    }

    /**
     * Opens the link inside Whatsapp
     */
    public void openInstagram() throws SocialException {
        try {
            if (getInstagram().equals(null) || getInstagram().equals("null")) {
                throw new SocialException("No Instagram Link");
            }
            if (getInstagram().startsWith(SCHEME) || getInstagram().startsWith(SCHEMES)) {
                URI uri = new URI(getInstagram());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            } else {
                URI uri = new URI(SCHEME + getInstagram());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            }
        } catch (URISyntaxException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (IOException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (SocialException e) {
            throw new SocialException("No Instagram Link");
        }
    }

    /**
     * Opens the link inside Preferred
     */
    public void openPreferred() throws SocialException {
        try {
            if (getPreferredLink().equals(null) || getPreferredLink().equals("null")) {
                throw new SocialException("No Preferred Link");
            }
            if (getPreferredLink().startsWith(SCHEME) || getPreferredLink().startsWith(SCHEMES)) {
                URI uri = new URI(getPreferredLink());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            } else {
                URI uri = new URI(SCHEME + getPreferredLink());
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(uri);
            }
        } catch (URISyntaxException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (IOException e) {
            throw new SocialException(MESSAGE_BAD_LINK);
        } catch (SocialException e) {
            throw new SocialException("No Preferred Link");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Social // instanceof handles nulls
                && this.toString().equals(((Social) other).toString())); // state check
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            builder.append(links[i - 1]);
            builder.append(" ");
        }
        builder.append(preferred);
        builder.append(" ");
        builder.append(links[4]);
        return builder.toString();
    }
}
