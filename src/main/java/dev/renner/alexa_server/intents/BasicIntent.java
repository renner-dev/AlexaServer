package dev.renner.alexa_server.intents;

import dev.renner.alexa_server.alexa.AlexaRequest;
import dev.renner.alexa_server.alexa.AlexaResponse;
import dev.renner.alexa_server.alexa.response.Intent;

/**
 * Created by renne on 27.06.2017.
 */
@Intent(name = "basic", executionMethod = "execute")
public class BasicIntent {

    public AlexaResponse execute(AlexaRequest request) {
        return null;
    }

}
