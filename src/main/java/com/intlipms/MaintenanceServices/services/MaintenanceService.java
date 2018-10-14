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
package com.intlipms.MaintenanceServices.services;

import com.intlipms.MaintenanceServices.exception.ResourceNotFoundException;
import com.intlipms.MaintenanceServices.model.PropertyAreas;
import com.intlipms.MaintenanceServices.model.PropertyItem;
import com.intlipms.MaintenanceServices.payload.PropertyAreaRequest;
import com.intlipms.MaintenanceServices.payload.PropertyAreaResponse;
import com.intlipms.MaintenanceServices.repository.PropertyAreaRepository;
import com.intlipms.MaintenanceServices.security.UserPrincipal;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adedamola
 */
@Service
public class MaintenanceService {

    @Autowired
    PropertyAreaRepository propertyAreaRepository;

    public PropertyAreas createPropertyArea(PropertyAreaRequest propertyAreaRequest) {

        PropertyAreas propertyAreas = new PropertyAreas();
        propertyAreas.setAreaName(propertyAreaRequest.getAreaName());
        propertyAreas.setAreaDescription(propertyAreaRequest.getAreaDescription());

        propertyAreaRequest.getPropertyItems().forEach(propertyRequest -> {
            propertyAreas.addPropertyItem(propertyRequest);
        });

        Instant now = Instant.now();

        propertyAreas.setExpirationDateTime(now);

        return propertyAreaRepository.save(propertyAreas);
    }
    
    public PropertyAreaResponse getPropertyAreaById(Long areaId) {
        PropertyAreas propertyAreas = propertyAreaRepository.findById(areaId)
                .orElseThrow(
                () -> new ResourceNotFoundException("PropertyArea", "id", areaId));
        PropertyAreaResponse par = new PropertyAreaResponse();
        par.setAreaDescription(propertyAreas.getAreaDescription());
        par.setAreaName(propertyAreas.getAreaName());
        par.setPropertyItems(propertyAreas.getPropertyItems());
        
        return par;
   
    }

}
