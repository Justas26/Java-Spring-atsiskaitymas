package com.krepsinis.model;

import com.krepsinis.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class KrepsinisDAOImpl implements KrepsinisDAO {
    public void insertEntity(Krepsinis krepsinis){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(krepsinis);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Krepsinis finEntityByID(int id) {
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        List <Krepsinis> krepsinis=entityManager.
                createQuery("SELECT c FROM Krepsinis c WHERE c.id=:id ")
                .setParameter("id",id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return krepsinis.get(0);
    }
    public List<Krepsinis>findEntities(){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        List<Krepsinis>krepsinis=entityManager.
                createQuery("SELECT c FROM Krepsinis c ")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return krepsinis;
    }
    public void updateEntity(Krepsinis krepsinis){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        Krepsinis krepsinis2=entityManager.find(Krepsinis.class,krepsinis.getId());
        krepsinis2.setTeamname(krepsinis.getTeamname());
        krepsinis2.setNamesurname(krepsinis.getNamesurname());
        krepsinis2.setLeague(krepsinis.getLeague());
        krepsinis2.setSponsors(krepsinis.getSponsors());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void removeEntityByID(int id){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        Krepsinis krepsinis=entityManager.find(Krepsinis.class,id);
        entityManager.remove(krepsinis);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<Krepsinis> search( String teamname ) {
        if (teamname.equals("")) {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            List<Krepsinis> krepsinis = entityManager.
                    createQuery("SELECT c FROM Krepsinis c ")
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
            return krepsinis;
        } else {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            List<Krepsinis> krepsinis = entityManager.
                    createQuery("SELECT c FROM Krepsinis  c WHERE c.teamname LIKE :teamname")

                    .setParameter("teamname", teamname)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return krepsinis;
        }

    }
    }


