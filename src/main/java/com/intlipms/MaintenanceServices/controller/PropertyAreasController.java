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
import com.intlipms.MaintenanceServices.payload.PropertyAreaRequest;
import com.intlipms.MaintenanceServices.payload.PropertyAreaResponse;
import com.intlipms.MaintenanceServices.repository.PropertyAreaRepository;
import com.intlipms.MaintenanceServices.services.MaintenanceService;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    PropertyAreaRepository propertyAreaRepository;
    
    @Autowired
    MaintenanceService maintenanceService;

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> createPropertyArea(@Valid @RequestBody PropertyAreaRequest propertyAreasRequest) {

        // Creating Property Area 
        PropertyAreas propertyAreas = maintenanceService.createPropertyArea(propertyAreasRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{areaId}")
                .buildAndExpand(propertyAreas.getId()).toUri();       

        return ResponseEntity.created(location).body(new ApiResponse(true, "Property Area created successfully"));

    }
    
    

}
