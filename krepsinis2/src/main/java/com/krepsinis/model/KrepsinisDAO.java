package com.krepsinis.model;


import java.util.List;

public interface KrepsinisDAO {
    void insertEntity(Krepsinis krepsinis);
    Krepsinis finEntityByID(int id);
    List<Krepsinis>findEntities();
    void updateEntity(Krepsinis krepsinis);
    void removeEntityByID(int id);
    List<Krepsinis>search( String teamname);
}
