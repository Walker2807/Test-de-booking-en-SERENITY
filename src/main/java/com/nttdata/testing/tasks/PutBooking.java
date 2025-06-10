package com.nttdata.testing.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.screenplay.Actor;

import static com.nttdata.testing.tasks.LoginTask.TOKEN_KEY;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutBooking implements Task {

    private final int bookingId;
    private final String body;

    public PutBooking(int bookingId, String body) {
        this.bookingId = bookingId;
        this.body = body;
    }

    public static PutBooking withData(int bookingId, String body) {
        return instrumented(PutBooking.class, bookingId, body);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String token = actor.recall(TOKEN_KEY);
        actor.attemptsTo(
                Put.to("/booking/" + bookingId)
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .header("Accept", "application/json")
                                .header("Cookie", "token=abc123")
                                .body(body)
                        )
        );
    }
}
