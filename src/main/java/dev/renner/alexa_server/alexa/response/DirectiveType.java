package dev.renner.alexa_server.alexa.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renne on 27.06.2017.
 */
public enum DirectiveType {

    @SerializedName("AudioPlayer.Stop")
    AUDIO_PLAYER_STOP,
    @SerializedName("AudioPlayer.Play")
    AUDIO_PLAYER_PLAY


}
