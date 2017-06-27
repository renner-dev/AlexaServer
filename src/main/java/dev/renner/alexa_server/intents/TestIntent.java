package dev.renner.alexa_server.intents;

import dev.renner.alexa_server.alexa.AlexaRequest;
import dev.renner.alexa_server.alexa.AlexaResponse;
import dev.renner.alexa_server.alexa.response.Intent;
import dev.renner.alexa_server.alexa.response.OutputSpeechType;

/**
 * Created by renne on 27.06.2017.
 */
@Intent(name = "test", executionMethod = "execute")
public class TestIntent extends BasicIntent {

    @Override
    public AlexaResponse execute(AlexaRequest request) {

        AlexaResponse alexaResponse = new AlexaResponse();
        alexaResponse.response.outputSpeech.type = OutputSpeechType.PlainText;
        alexaResponse.response.outputSpeech.text = "Yo Test Digga";
        alexaResponse.response.outputSpeech.ssml = null;
        alexaResponse.response.reprompt = null;
        alexaResponse.response.directives = null;

        return alexaResponse;
    }

}
