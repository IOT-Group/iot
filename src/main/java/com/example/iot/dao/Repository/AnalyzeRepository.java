package com.example.iot.dao.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeRepository {
    public abstract void analyze(String deviceId,String code);
}
