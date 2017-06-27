package dev.renner.alexa_server.intents;

import dev.renner.alexa_server.alexa.AlexaRequest;
import dev.renner.alexa_server.alexa.AlexaResponse;
import dev.renner.alexa_server.alexa.response.Directive;
import dev.renner.alexa_server.alexa.response.DirectiveType;
import dev.renner.alexa_server.alexa.response.Intent;

/**
 * Created by renne on 27.06.2017.
 */
@Intent(name = "AMAZON.PauseIntent", executionMethod = "execute")
public class AMAZONPauseIntent extends BasicIntent {

    public AlexaResponse execute(AlexaRequest request) {

        AlexaResponse alexaResponse = new AlexaResponse();

        alexaResponse.response.card = null;
        alexaResponse.response.outputSpeech = null;
        alexaResponse.response.reprompt = null;
        Directive playPietCast = new Directive();
        {
            playPietCast.type = DirectiveType.AUDIO_PLAYER_STOP;
            playPietCast.playBehavior = null;
            playPietCast.audioItem = null;
        }
        alexaResponse.response.directives.add(playPietCast);

        return alexaResponse;

    }

}
