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
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adedamola
 */

@Entity
@Table(name = "property_areas")
public class PropertyAreas extends UserDateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="property_area_id")
    private Long id;

    @NotBlank
    @Size(max=20)
    private String areaName;

    @NotBlank
    @Size(max=120)
    private String areaDescription;
    
    @OneToMany(
            mappedBy = "propertyArea",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @Size(min=1, max=45)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    private List<PropertyItem> propertyItems = new ArrayList<>(); 
    
    @NotNull
    private Instant expirationDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAreaName(){
        return areaName;
    }
    
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }
    
    public String getAreaDescription(){
        return areaDescription;
    }
    
    public void setAreaDescription(String areaDescription){
        this.areaDescription = areaDescription;
    }
    
    public Instant getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(Instant expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }
    
    public List<PropertyItem> getPropertyItems(){
        return propertyItems;
    }
    
    public void setPropertyItems(List<PropertyItem> propertyItem){
        this.propertyItems = propertyItem;
    }
    
    public void addPropertyItem(PropertyItem propertyItem){
        propertyItems.add(propertyItem);
        propertyItem.setPropertyArea(this);
    }
    
    public void removePropertyItem(PropertyItem propertyItem){        
        propertyItems.remove(propertyItem);
        propertyItem.setPropertyArea(null);
    }   
        
}
