@RestController
@RequestMapping("/flights/all")
public class FlightController {
	@PostMapping
    public ResponseEntity<?> addFlights(@RequestBody FlightRequest flightRequest) {
        try {
            // Validation des données
            if (flightRequest.getAllFlights() == null || flightRequest.getAllFlights().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid request format"));
            }
         // Retourner une réponse de succès
            return ResponseEntity.ok(Map.of(
                "message", "Flights data successfully updated",
                "updatedFlights", updatedFlights
            ));
        } catch (Exception e) {
            // Gérer les erreurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error"));
        }
    }
}