package com.example.pharmaceuticalsales.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
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

    public Long getRestoreId() {
        return restoreId;
    }

    public void setRestoreId(Long restoreId) {
        this.restoreId = restoreId;
    }

    public Date getRestoreDate() {
        return restoreDate;
    }

    public void setRestoreDate(Date restoreDate) {
        this.restoreDate = restoreDate;
    }

    public DataBackup getDataBackup() {
        return dataBackup;
    }

    public void setDataBackup(DataBackup dataBackup) {
        this.dataBackup = dataBackup;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
