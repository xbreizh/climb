package org.example.demo.climb.business.impl;


import org.example.demo.climb.business.contract.CountryManager;
import org.example.demo.climb.business.contract.MemberManager;
import org.example.demo.climb.business.contract.SpotManager;
import org.example.demo.climb.consumer.contract.SpotDao;
import org.example.demo.climb.model.ClimbingType;
import org.example.demo.climb.model.bean.Country;
import org.example.demo.climb.model.bean.Spot;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Named("spotManager")
public class SpotManagerImpl  implements SpotManager {

    private Class cl=Spot.class;
    @Inject
    private SpotDao spotDao;
    @Inject
    private MemberManager memberManager;
    @Inject
    private CountryManager countryManager;

    // Create
    @Override
    public void addSpot(Spot spot) {
        // Setting Spot Name and CityWith Upper Case
        spot.setCity(spot.getCity().toUpperCase());
        spot.setName(toCamelCase(spot.getName()));
        spotDao.add(spot);
    }

    // Get
    @Override
    public Spot getSpotById(Integer id) {
        return spotDao.getById(id);
    }

    @Override
    public Spot getSpotByName(String login) {
        return spotDao.getSpotByName(login);
    }

    // Get List
    @Override
    public List<Spot> getListSpot(String str, String climbingType, String hasTopo, int levelMin, int levelMax) {
        String  ct = "ALL";
        String  htopo = "";
        for(ClimbingType clt: ClimbingType.values()){
            if(clt.getName().equals(climbingType)){
                ct = clt.getName().toUpperCase();
            }
        }
        System.out.println("Has topo: "+hasTopo);
        if(hasTopo!=null) {
            if (hasTopo.equals("true")) {
                htopo = "is not null";
            }
        }
        str = str.toUpperCase();
        if(str == null){
            System.out.println("str was null");
            str="";
        }
        if(levelMin == 0){levelMin=1;}
        if(levelMax == 0){levelMax=32;}
        System.out.println("str: "+str+" climb: "+ct+" topo: "+htopo);
       return spotDao.ListSpotByCriterias(str, ct, htopo, levelMin, levelMax);
    }

    @Override
    public List<Spot> getListSpot() {
        return spotDao.getAll();
    }
    @Override
    public List<String> getListCityByCountry(Country country) {
        List<String> cityList = new ArrayList<>();
        for (Spot spot:spotDao.ListSpotByCountry(country)
             ) {
            cityList.add(spot.getCity());
        }
        return cityList;
    }

    @Override
    public List<String> getListCity() {
        return spotDao.ListCity();
    }

    @Override
    public List<String> getListCityByContinent(String continent) {
        return null;
    }

    @Override
    public List<Spot> getListSpotByContinent(String continent) {
        return spotDao.ListSpotByContinent(continent);
    }

    @Override
    public List<Spot> getListSpotByCountry(Country country) {
        return spotDao.ListSpotByCountry(country);
    }


    // Update
    @Override
    public void updateSpot(Spot spot) {
        System.out.println("spot received: "+spot);
     try{spotDao.update(spot);}
     catch(NullPointerException e){
         System.out.println("spot couldn t be found: "+spot.getName());
     }


    }

    @Override
    public void updateWhenDeletingMember(int id) {
        System.out.println("trying to update member spots before deleting");
        spotDao.updateWhenDeletingMember(1, id);
    }

    // Delete

    @Override
    public void deleteSpot(Spot spot) {
        try{
            spotDao.delete(spot);}
        catch(NullPointerException e){
            System.out.println("spot couldn t be deleted: ");
        }
    }
    public static String toCamelCase(final String init) {
        if (init==null)
            return null;

        final StringBuilder ret = new StringBuilder(init.length());

        for (final String word : init.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length()==init.length()))
                ret.append(" ");
        }

        return ret.toString();
    }

}
