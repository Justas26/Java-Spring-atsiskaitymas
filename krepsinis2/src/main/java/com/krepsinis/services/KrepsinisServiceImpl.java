package com.krepsinis.services;

import com.krepsinis.model.Krepsinis;
import com.krepsinis.model.KrepsinisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class KrepsinisServiceImpl implements KrepsinisService {
    @Autowired
    @Qualifier("KrepsinisDAO")
    private KrepsinisDAO krepsinisDAO;

    @Override
    public List<Krepsinis> getAll() {
        return krepsinisDAO.findEntities();
    }
    @Override
    public void save(Krepsinis krepsinis) {
       krepsinisDAO.insertEntity(krepsinis);
    }

    @Override
    public Krepsinis getById(int id) {
        return krepsinisDAO.finEntityByID(id);
    }

    @Override
    public void update(Krepsinis krepsinis) {
        krepsinisDAO.updateEntity(krepsinis);

    }

    @Override
    public void delete(int id) {
        krepsinisDAO.removeEntityByID(id);

    }

    @Override
    public List<Krepsinis> search(@RequestParam(value = "teamname") String teamname) {
        return krepsinisDAO.search(teamname);
    }


}
