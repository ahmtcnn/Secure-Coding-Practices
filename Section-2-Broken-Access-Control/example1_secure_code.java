@GetMapping("/vehicle/{carId}/location")
public ResponseEntity<?> getLocationBOLA(@PathVariable("carId") UUID carId) {
    // Assume that we can access the currently authenticated user's details directly
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = (User) authentication.getPrincipal();

    // Check if the current user owns the car or has permission to view it
    if (!vehicleService.userOwnsCar(currentUser.getId(), carId)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new CRAPIResponse("You are not authorized to view this vehicle's location."));
    }

    VehicleLocationResponse vehicleDetails = vehicleService.getVehicleLocation(carId);
    if (vehicleDetails != null) {
        return ResponseEntity.ok().body(vehicleDetails);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CRAPIResponse(UserMessage.DID_NOT_GET_VEHICLE_FOR_USER));
    }
}

