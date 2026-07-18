package com.ritesh.app.drivingalertapp.controller;

import com.ritesh.app.drivingalertapp.model.DrivingAlert;
import com.ritesh.app.drivingalertapp.service.DrivingAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
@Tag(name = "Driving Alerts", description = "Driving alert management APIs")
public class DrivingAlertController {

    @Autowired
    private DrivingAlertService alertService;

    @PostMapping("/createAlertEvent")
    @Operation(summary = "Create a new driving alert event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alert created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    private int createAlertEvent(@RequestBody DrivingAlert alert){
        int response = -1;
        alert.setTimestamp(new Date().toString());
        alertService.saveOrUpdate(alert);
        return alert.getAlertId();
    }

    @GetMapping("/alert/{alertId}")
    @Operation(summary = "Get alert by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alert found"),
            @ApiResponse(responseCode = "404", description = "Alert not found")
    })
    private DrivingAlert getAlert(@PathVariable("alertId") int id){
        DrivingAlert response = null;
        response = alertService.getEmployeeById(id);
        return response;
    }
}
