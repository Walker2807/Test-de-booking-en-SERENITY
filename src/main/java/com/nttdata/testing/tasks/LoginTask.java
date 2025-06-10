package com.nttdata.testing.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class    LoginTask implements Task {

    public static final String TOKEN_KEY = "authToken";

    public static LoginTask perform() {
        return instrumented(LoginTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String body = """
            {
              "username": "admin",
              "password": "password123"
            }
            """;

        actor.attemptsTo(
                Post.to("/auth")
                        .with(req -> req
                                .header("Content-Type", "application/json")
                                .body(body)
                        )
        );

        String token = SerenityRest.lastResponse().jsonPath().getString("token");
        actor.remember(TOKEN_KEY, token);
    }
}
