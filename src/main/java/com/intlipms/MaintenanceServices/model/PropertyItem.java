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
package com.intlipms.MaintenanceServices.model;

import com.intlipms.MaintenanceServices.model.audit.UserDateAudit;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Adedamola
 */
@Entity
@Table(name="property_items")
public class PropertyItem extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 45)
    private String propertyItem;

    @NotBlank
    @Size(max = 120)
    private String propertyDescription;

    @JoinColumn(name = "property_area_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)    
    private PropertyAreas propertyArea;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPropertyItem(){
        return this.propertyItem;
    }
    
    public void setPropertyItem(String propertyItem){
        this.propertyItem = propertyItem;
    }
    
    public String getPropertyDescription(){        
        return this.propertyDescription;
    }
    
    public void setPropertyDescription(String propertyDescription){
        this.propertyDescription = propertyDescription;
    }
    
    public PropertyAreas getPropertyArea(){
        return propertyArea;
    }
    
    public void setPropertyArea(PropertyAreas propertyArea){
        this.propertyArea = propertyArea;
    }

}
