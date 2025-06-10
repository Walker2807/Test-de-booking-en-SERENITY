package com.nttdata.testing.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.Actor;
import static com.nttdata.testing.tasks.LoginTask.TOKEN_KEY;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteBooking implements Task {

    private final int bookingId;

    public DeleteBooking(int bookingId) {
        this.bookingId = bookingId;
    }

    public static DeleteBooking withId(int bookingId) {
        return instrumented(DeleteBooking.class, bookingId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String token = actor.recall(TOKEN_KEY);
        actor.attemptsTo(
                Delete.from("/booking/" + bookingId)
                        .with(request -> request
                                .header("Cookie", "token=" + token)
                        )
        );
    }
}
