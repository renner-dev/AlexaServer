package dev.renner.alexa_server;

import static spark.Spark.post;

public class Startup {
    public static void main(String[] args) {

        IntentHelper.setup();

        System.out.println(ResponseBuilder.getBasicResponse());

        post("/*", (request, response) -> {

            System.out.println(request.pathInfo());

            response.status(200);
            response.type("application/json;charset=UTF-8");


            return ResponseBuilder.getResponse(request.body());
        });


    }
}