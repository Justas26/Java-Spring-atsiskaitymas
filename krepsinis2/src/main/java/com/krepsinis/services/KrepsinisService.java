package com.krepsinis.services;


import com.krepsinis.model.Krepsinis;


import java.util.List;

public interface KrepsinisService {

        List<Krepsinis>getAll();
        void save(Krepsinis krepsinis);

        Krepsinis getById(int id);

        void update(Krepsinis krepsinis);

        void delete(int id);

        List<Krepsinis> search( String teamname);
}
