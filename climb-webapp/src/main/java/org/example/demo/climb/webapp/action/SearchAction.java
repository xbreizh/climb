package org.example.demo.climb.webapp.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.example.demo.climb.business.contract.*;
import org.example.demo.climb.model.bean.*;
import org.example.demo.climb.model.exception.NotFoundException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SearchAction extends LoginAction implements SessionAware {

    private int id;
    private List<Spot> spotList=new ArrayList<>();
    private List<Topo> topoList=new ArrayList<>();
    private Spot spot;
    private Topo topo;
    private String str;
    private String climbingType;
    private String hasTopo;

    @Inject
    private SearchManager searchManager;


    // Methods

    public String doSearchByKeyword() throws  NotFoundException {
        String vResult = ActionSupport.INPUT;
        if (str != null) {
            if (str.equals("")) {
                this.addActionError("you must enter a keyword");
            } else {
                System.out.println("keyword valid: "+str);
                spotList=searchManager.findSpotByString(str);
                topoList=searchManager.findTopoByString(str);
                return ActionSupport.SUCCESS;
            }
        }
        return vResult;
    }
    public String doListTopo() throws  NotFoundException{
        String vResult= ActionSupport.SUCCESS;
        System.out.println("trying to get a list with keyword: "+str);
        System.out.println("climbing type passed: "+climbingType);
        System.out.println("has topo passed: "+hasTopo);
        spotList=searchManager.findSpotByString(str);
        topoList=searchManager.findTopoByString(str);
        System.out.println("size spot: "+spotList.size());
        System.out.println("size topo: "+topoList.size());
        return ActionSupport.SUCCESS;
    }



    // Getters and Setters

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getClimbingType() {
        return climbingType;
    }

    public void setClimbingType(String climbingType) {
        this.climbingType = climbingType;
    }

    public String getHasTopo() {
        return hasTopo;
    }

    public void setHasTopo(String hasTopo) {
        this.hasTopo = hasTopo;
    }

}
