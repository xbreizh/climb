package org.example.demo.climb.consumer.impl;

import org.example.demo.climb.consumer.contract.CountryDao;
import org.example.demo.climb.model.bean.Country;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
/*
@Named*/
public class CountryDaoImpl  /*implements CountryDao*/ {
/*

    private Class cl= Country.class;

    @Inject
    private SessionFactory sessionFactory;



    @Override
    public List getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Country ").list();
    }



    @Override
    public List<String> getListContinent() {
      List<String> continentList=sessionFactory.getCurrentSession().createQuery(
                "select distinct z.continent from Country z", String.class).getResultList();
        return continentList;
    }

    @Override
    public List<String> getListCountry(String continent) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select distinct z.country from Country z where z.continent=:continent");
        query.setParameter("continent", continent);

        return query.getResultList();
    }

*/

}