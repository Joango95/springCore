package com.joango.DAO;

import com.joango.model.User;

public class UserDAO implements User {

    private long id;
    private String name;
    private String email;

    public UserDAO(long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDAO(String name, String email){
        this.name = name;
        this.email = email;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
