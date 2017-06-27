package dev.renner.alexa_server;

import com.icosillion.podengine.exceptions.InvalidFeedException;
import com.icosillion.podengine.exceptions.MalformedFeedException;

import java.net.MalformedURLException;

import static spark.Spark.post;

public class Startup {
    public static void main(String[] args) throws MalformedURLException, InvalidFeedException, MalformedFeedException {
        IntentHelper.setup();
        ResponseBuilder.setup();

        System.out.println(ResponseBuilder.getBasicResponse());

        post("/*", (request, response) -> {

            System.out.println(request.pathInfo());

            response.status(200);
            response.type("application/json;charset=UTF-8");


            return ResponseBuilder.getResponse(request.body());
        });


    }
}