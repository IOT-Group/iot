package com.example.iot.dao.Repository;

public interface UserRepository {
    public abstract void register(String username,String password);
    public abstract boolean login(String username,String password);
}
