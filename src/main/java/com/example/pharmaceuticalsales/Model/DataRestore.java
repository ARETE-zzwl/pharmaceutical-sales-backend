package com.example.pharmaceuticalsales.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "data_restore")
public class DataRestore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restoreId;

    private Date restoreDate;

    @ManyToOne
    @JoinColumn(name = "backup_id", nullable = false)
    private DataBackup dataBackup;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    // Getters and Setters

}
