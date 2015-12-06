package com.thens.example.model;

import com.thens.generic.util.GenericEntity;

import javax.persistence.*;

@Entity
public class Admin implements GenericEntity<AdminDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;

    @Column(length = 20)
    private String username;

    @Column(length = 20)
    private String password;

    @Column(length = 60)
    private String name;

    @Column(length = 60)
    private String surname;

    @Column(length = 254)
    private String email;

    public AdminDTO toDTO() {
        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setAdminId(getAdminId());
        adminDTO.setUsername(getUsername());
        adminDTO.setPassword(getPassword());
        adminDTO.setName(getName());
        adminDTO.setSurname(getSurname());
        adminDTO.setEmail(getEmail());

        return adminDTO;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
