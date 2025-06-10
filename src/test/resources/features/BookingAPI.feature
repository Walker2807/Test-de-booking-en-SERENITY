@Booking
Feature: APIS from Booking

  Como usuario de Booking
  Quiero obtener la lista de bookings
  Para poder verificar los detalles de los registros

  @CP01_Booking
  Scenario: Obtener todas los booking exitosamente
    Given el actor establece el endpoint para obtener la lista de booking
    When el actor realiza una solicitud GET
    Then el codigo de respuesta debe ser 200

  @CP02_Booking
  Scenario: Actualizar una reserva existente exitosamente
    Given el actor establece el endpoint PUT para actualizar una reserva con id 1
    When el actor envia una solicitud PUT con los datos actualizados de la reserva
    Then el codigo de respuesta debe ser 200

  @CP03_Booking
  Scenario: Eliminar una reserva existente exitosamente
    Given el actor establece el endpoint DELETE para eliminar una reserva con id 1
    When el actor envia una solicitud DELETE
    Then el codigo de respuesta debe ser 201
