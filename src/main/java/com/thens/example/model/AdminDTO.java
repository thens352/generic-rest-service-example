package com.thens.example.model;

import com.thens.generic.util.GenericDTO;

import java.util.Objects;

public class AdminDTO extends GenericDTO<Admin>{
    private int adminId;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    @Override
    public Admin toEntity() {
        Admin admin = new Admin();

        admin.setAdminId(getAdminId());
        admin.setUsername(getUsername());
        admin.setPassword(getPassword());
        admin.setName(getName());
        admin.setSurname(getSurname());
        admin.setEmail(getEmail());

        return admin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO adminDTO = (AdminDTO) o;
        return Objects.equals(adminId, adminDTO.adminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId);
    }
}
