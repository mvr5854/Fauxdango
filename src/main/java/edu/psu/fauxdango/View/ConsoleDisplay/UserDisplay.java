package edu.psu.fauxdango.View.ConsoleDisplay;

import edu.psu.fauxdango.Model.User;
import edu.psu.fauxdango.Util.IOHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDisplay {
    private final static Logger logger = LogManager.getRootLogger();

    public static User registerUser() {
        User user = new User();

        user.setFirstName(IOHelper.readValidNameFromKeyboard("Enter first name"));
        user.setLastName(IOHelper.readValidNameFromKeyboard("Enter last name"));
        user.setEmailAddress(IOHelper.readValidEmailAddressFromKeyboard("Enter email address"));

        logger.info("New user: " + user.toString());

        return user;
    }
}
