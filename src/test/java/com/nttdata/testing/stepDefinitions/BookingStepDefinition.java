package com.nttdata.testing.stepDefinitions;

import com.nttdata.testing.questions.ResponseCode;
import com.nttdata.testing.tasks.GetAllBookings;
import com.nttdata.testing.tasks.LoginTask;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.testing.tasks.PutBooking;
import com.nttdata.testing.tasks.DeleteBooking;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class BookingStepDefinition {

    public static Logger LOGGER = LoggerFactory.getLogger(BookingStepDefinition.class);
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint para obtener la lista de booking")
    public void elActorEstableceElEndpointParaObtenerLaListaDeBooking(Actor actor) {
        actor.whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }

    @When("el {actor} realiza una solicitud GET")
    public void elActorRealizaUnaSolicitudGET(Actor actor) {
        actor.attemptsTo(GetAllBookings.fromEndpoint("/booking"));
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("El código de respuesta", ResponseCode.getStatus(), equalTo(responseCode)));
    }

    @When("el actor envia una solicitud PUT con los datos actualizados de la reserva")
    public void elActorEnviaUnaSolicitudPUTConLosDatosActualizadosDeLaReserva(Actor actor, int id) {
        actor.whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }
    @When("el {actor} envia una solicitud PUT con los datos actualizados de la reserva")
    public void enviarPutActualizacion(Actor actor) {
        actor.attemptsTo(LoginTask.perform());
        String body = """
        {
          "firstname": "James",
          "lastname": "Brown",
          "totalprice": 111,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2023-01-01",
            "checkout": "2023-01-10"
          },
          "additionalneeds": "Breakfast"
        }
        """;
        actor.attemptsTo(PutBooking.withData(1, body));
    }

    @Given("el actor establece el endpoint DELETE para eliminar una reserva con id {int}")
    public void elActorEstableceElEndpointDELETEParaEliminarUnaReservaConId(Actor actor, int id) {
        actor.whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }

    @When("el {actor} envia una solicitud DELETE")
    public void enviarDelete(Actor actor) {
        actor.attemptsTo(LoginTask.perform());
        actor.attemptsTo(DeleteBooking.withId(1));
    }
}