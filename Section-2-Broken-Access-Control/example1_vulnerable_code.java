@GetMapping("/vehicle/{carId}/location")
public ResponseEntity<?> getLocationBOLA(@PathVariable("carId") UUID carId) {
    VehicleLocationResponse vehicleDetails = vehicleService.getVehicleLocation(carId);
    if (vehicleDetails != null) {
        return ResponseEntity.ok().body(vehicleDetails);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CRAPIResponse(UserMessage.DID_NOT_GET_VEHICLE_FOR_USER));
    }
}

