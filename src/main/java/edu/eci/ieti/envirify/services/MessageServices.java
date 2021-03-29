package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Message;

public interface MessageServices {

    /**
     * Adds a new message to the Envirify Message
     * @param message message to be added
     * @param email email of the user that adds the message
     * @throws EnvirifyPersistenceException in case messages are sent or received from users that do not exist
     */
    public void addMessage(Message message, String email) throws EnvirifyPersistenceException;
}
