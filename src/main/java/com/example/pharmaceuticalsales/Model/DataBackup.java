package com.example.pharmaceuticalsales.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "data_backup")
public class DataBackup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long backupId;

    private Date backupDate;

    @Lob
    private String backupContent;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    // Getters and Setters
}
