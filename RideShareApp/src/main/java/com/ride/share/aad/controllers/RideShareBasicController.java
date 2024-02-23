package com.ride.share.aad.controllers;

import com.ride.share.aad.config.scurity.exceptions.InvalidAuthRequest;
import com.ride.share.aad.storage.entity.Ride;
import com.ride.share.aad.utils.auth.RequestAuthUtils;
import com.ride.share.aad.utils.entity.RideUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rs")
@CrossOrigin(origins = "http://localhost:3000")
public class RideShareBasicController {

    @GetMapping("/home/text")
    @ResponseBody
    public String homeText(@RequestHeader("Authorization") String authorizationHeader) throws InvalidAuthRequest {
        if (!RequestAuthUtils.isValidToken(authorizationHeader)) {
            throw new InvalidAuthRequest("Need to login before getting data");
        }
        return "This is RideShare Home, Welcome";
    }

    @GetMapping("/public/home/text")
    @ResponseBody
    public String publicHomeText() {
        return "This is RideShare Home, Public";
    }

    @GetMapping("/rides")
    public String getRides(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "timeOfRide") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws InvalidAuthRequest {
        boolean validToken = RequestAuthUtils.isValidToken(authorizationHeader);
        List<Ride> allRides = RideUtils.getAllRides();
        JSONObject data = new JSONObject();
        JSONArray rides = new JSONArray();
        for (Ride allRide : allRides) {
            rides.put(RideUtils.toJson(allRide, validToken));
        }
        data.put("rides", rides);
        return data.toString();
    }

    @ExceptionHandler(InvalidAuthRequest.class)
    public ResponseEntity<ErrorResponse> handleAuthException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.UNAUTHORIZED, ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
