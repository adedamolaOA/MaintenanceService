/*
 * The MIT License
 *
 * Copyright 2018 Adedamola.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.intlipms.MaintenanceServices.controller;

import com.intlipms.MaintenanceServices.exception.AppException;
import com.intlipms.MaintenanceServices.model.PropertyAreas;
import com.intlipms.MaintenanceServices.model.Role;
import com.intlipms.MaintenanceServices.model.RoleName;
import com.intlipms.MaintenanceServices.model.User;
import com.intlipms.MaintenanceServices.payload.ApiResponse;
import com.intlipms.MaintenanceServices.repository.PropertyAreaRepository;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Adedamola
 */
@RestController
@RequestMapping("/api/property")
public class PropertyAreasController {

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    PropertyAreaRepository propertyAreaRepository;

    @PostMapping("/area")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody PropertyAreas propertyAreasRequest) {

        // Creating Property Area 
        PropertyAreas propertyAreas = new PropertyAreas();
        propertyAreas.setAreaDescription(propertyAreasRequest.getAreaDescription());
        propertyAreas.setAreaName(propertyAreasRequest.getAreaDescription());
        propertyAreas.setExpirationDateTime(Instant.EPOCH);
        //propertyAreas.setAreaDescription(propertyAreasRequest.getAreaDescription());
        //propertyAreas.setAreaDescription(propertyAreasRequest.getAreaDescription());
        
        PropertyAreas result = propertyAreaRepository.save(propertyAreas);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/property/area/{areaName}")
                .buildAndExpand(result.getAreaName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Property Area created successfully"));

    }

}
