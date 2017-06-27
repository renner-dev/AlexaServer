package dev.renner.alexa_server.intents;

import com.icosillion.podengine.exceptions.MalformedFeedException;
import com.icosillion.podengine.models.Episode;
import com.icosillion.podengine.models.Podcast;
import dev.renner.alexa_server.alexa.AlexaRequest;
import dev.renner.alexa_server.alexa.AlexaResponse;
import dev.renner.alexa_server.alexa.response.*;

import java.net.URL;
import java.util.UUID;

/**
 * Created by renne on 27.06.2017.
 */
@Intent(name = "pietcast", executionMethod = "execute")
public class PietCastIntent extends BasicIntent {

    public AlexaResponse execute(AlexaRequest request) {

        Episode newestEpisode = null;
        try {
            Podcast podcast = new Podcast(new URL("http://www.pietsmiet.de/pietcast/feed/podcast/"));
            System.out.printf("💼 %s has %d episodes!\n", podcast.getTitle(), podcast.getEpisodes().size());

            newestEpisode = podcast.getEpisodes().get(0);
            System.out.println("- " + newestEpisode.getTitle() + ": " + newestEpisode.getEnclosure().getURL());


        } catch (Exception e) {
            e.printStackTrace();
        }

        AlexaResponse alexaResponse = new AlexaResponse();
        if (newestEpisode == null) {
            alexaResponse.response.outputSpeech.type = OutputSpeechType.PlainText;
            alexaResponse.response.outputSpeech.text = "Ich konnte leider keinen PietCast finden!";
            alexaResponse.response.outputSpeech.ssml = null;
            alexaResponse.response.reprompt = null;
            alexaResponse.response.directives = null;
        } else {
            alexaResponse.response.outputSpeech.type = OutputSpeechType.PlainText;
            try {
                alexaResponse.response.outputSpeech.text = "Ich habe folgenden PietCast Gefunden: " + newestEpisode.getTitle();
            } catch (MalformedFeedException e) {
            }
            alexaResponse.response.outputSpeech.ssml = null;
            alexaResponse.response.reprompt = null;
            Directive playPietCast = new Directive();
            {
                playPietCast.type = DirectiveType.AUDIO_PLAYER_PLAY;
                playPietCast.playBehavior = "REPLACE_ALL";
                AudioItem audioItem = new AudioItem();
                {
                    Stream stream = new Stream();
                    {
                        stream.token = UUID.randomUUID().toString();
                        try {
                            stream.url = newestEpisode.getEnclosure().getURL().toString().replace("http://", "https://");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        stream.offsetInMilliseconds = 0;
                    }
                    audioItem.stream = stream;
                }
                playPietCast.audioItem = audioItem;
            }
            alexaResponse.response.directives.add(playPietCast);
        }

        return alexaResponse;
    }

}
