package dev.renner.alexa_server.alexa.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renne on 27.06.2017.
 */
public class Session {
    @SerializedName("new")
    public boolean new2;

    public Application application;

    public String sessionId;

    public Attributes attributes;

    public User user;
}
