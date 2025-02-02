package com.example.work3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Vendor_Name", nullable = false, length = 255)
    private String vendorName;

    @Column(name = "Business_Address", length = 255)
    private String businessAddress;

    @Column(name = "City", nullable = false, length = 255)
    private String city;

    @Column(name = "State", nullable = false, length = 255)
    private String state;

    @Column(name = "Pin_Code", nullable = false, length = 10)
    private String pinCode;

    @Column(name = "Latitude")
    private Double latitude;

    @Column(name = "Longitude")
    private Double longitude;

    @Column(name = "FSSAI_Number", unique = true)
    private Long fssaiNumber;

    @Column(name = "Contact_Number", nullable = false, length = 15)
    private String contactNumber;

    @Column(name = "Contact_Name", nullable = false, length = 255)
    private String contactName;

    public String getName(){
        return vendorName;
    }
}