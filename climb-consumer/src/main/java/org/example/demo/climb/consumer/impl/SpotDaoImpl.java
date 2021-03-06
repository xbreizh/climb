package org.example.demo.climb.consumer.impl;
import org.example.demo.climb.consumer.contract.MemberDao;
import org.example.demo.climb.consumer.contract.SpotDao;
import org.example.demo.climb.model.bean.Country;
import org.example.demo.climb.model.bean.Spot;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SpotDaoImpl implements SpotDao {

    private Class cl = Spot.class;
    @Inject
    private SessionFactory sessionFactory;
    @Inject
    private MemberDao memberDao;

    /*@Override*/
    public void add(Spot o) {
        sessionFactory.getCurrentSession().persist(o);
    }


    /*@Override*/
    public Spot getById(int id) {
        Query query=sessionFactory.getCurrentSession().createQuery(
                "From Spot where id=:n");
        query.setParameter("n", id);
        return (Spot) query.getSingleResult();
    }

    @Override
    public Spot getSpotByName(String name) {
        Query query=sessionFactory.getCurrentSession().createQuery(
                "From Spot where name=:n");
        query.setParameter("n", name);
        return (Spot) query.getSingleResult();
    }

    // Get List
   /* @Override*/
    public List getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Spot").list();
    }


    @Override
    public List<Spot> ListSpotByContinent(String continent) {
        Query query=sessionFactory.getCurrentSession().createQuery(
                "From Spot where country.continent=:c");
        query.setParameter("c", continent);
        return query.getResultList();
    }

    @Override
    public List<Spot> ListSpotByCountry(Country country) {
        Query query=sessionFactory.getCurrentSession().createQuery(
                "select spotList from Country c where c.id =:countryId");
        query.setParameter("countryId", country.getId());
        return query.getResultList();
    }

    @Override
    public List<Spot> ListSpotByCriterias(String str, String climbingType, String hasTopo, int levelMin, int levelMax) {
        Query q;
        int topoSize=sessionFactory.getCurrentSession().createQuery("From Topo ").getResultList().size();
        System.out.println("topo size: "+topoSize);
        if(!hasTopo.equals("") && topoSize >0 ){
            Query TopoQuery = sessionFactory.getCurrentSession().createQuery(
                    "select id from Spot where topos.size > 0");
            List<String> idList = TopoQuery.getResultList();
            // Getting list of Spot ids based on route grade criterias

            q = sessionFactory.getCurrentSession().createQuery(
                    "select spot.id from Route where grade >= :min and grade <= :max and spot.id in(:topoList)");
            q.setParameter("min", levelMin);
            q.setParameter("max", levelMax);
            q.setParameter("topoList", idList);
        }else{
        // Getting list of Spot ids based on route grade criterias

            q = sessionFactory.getCurrentSession().createQuery(
                    "select spot.id from Route where grade >= :min and grade <= :max");
            q.setParameter("min", levelMin);
            q.setParameter("max", levelMax);
            }

        List<String> idList = q.getResultList();
        if(climbingType.equals("ALL")){climbingType="";}
        System.out.println("list from dao: "+idList);
        System.out.println("climbing type: "+climbingType);
        if(!idList.isEmpty()) {
            Query query = sessionFactory.getCurrentSession().createQuery(
                    "From Spot  where id in (:list) and type like :t and (upper(name) like :n or upper(city) like :n or upper(description) like :n " + "or upper(country.name) like :n or upper(country.continent) like :n )");
            query.setParameter("n", "%" + str + "%".toUpperCase());
            query.setParameter("list", idList);
            query.setParameter("t", "%"+climbingType+"%");
            /*System.out.println("param: "+query.getParameter("list").toString());*/
            return query.getResultList();
        }else{
            return null;
        }
    }

    @Override
    public List<Spot> ListSpotByCity(String continent, String country, String city) {
        // no value
        if(continent.equals("")|| country.equals("")|| city.equals("")){

        }
        String str="From Spot where country.continent=:cn and country.name=:cr and city=:r";
        Query query=sessionFactory.getCurrentSession().createQuery(str
                );
        query.setParameter("cn", continent);
        query.setParameter("cr", country);
        query.setParameter("r", city);
  return query.getResultList();
    }

    @Override
    public List<String> ListCity() {
        Query query=sessionFactory.getCurrentSession().createQuery(
                "select distinct (city) from Spot ");

        return query.getResultList();
    }


   /* @Override*/
    public void update(Spot spot) {
        System.out.println("trying to update: "+spot.getName());
        sessionFactory.getCurrentSession().update(spot);
    }

    /*@Override*/
    public void delete(Spot spot) {
        System.out.println("spot received in dao: "+spot);
        sessionFactory.getCurrentSession().delete(cl.getName(), spot);
    }

    @Override
    public void updateWhenDeletingMember(int id1, int id2){
    }


}
