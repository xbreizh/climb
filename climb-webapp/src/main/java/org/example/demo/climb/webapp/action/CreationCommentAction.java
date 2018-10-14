package org.example.demo.climb.webapp.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.example.demo.climb.business.contract.CommentManager;
import org.example.demo.climb.business.contract.RouteManager;
import org.example.demo.climb.business.contract.SpotManager;
import org.example.demo.climb.model.bean.Comment;
import org.example.demo.climb.model.bean.Route;
import org.example.demo.climb.model.bean.Spot;
import org.example.demo.climb.model.exception.NotFoundException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreationCommentAction extends LoginAction implements SessionAware {

    private Comment comment;
    private int id;

    /*private String country;
    private List<Integer> heighList=new ArrayList<>();
    private List<String> gradeList=new ArrayList<>();
    private List<String> typeList=new ArrayList<>();*/
    private List<Comment> commentListRoute=new ArrayList<>();

    private List<Comment> commentListSpot=new ArrayList<>();


    private Route route;
    private Spot spot;


    @Inject
    private CommentManager commentManager;
    @Inject
    private RouteManager routeManager;
    @Inject
    private SpotManager spotManager;

    // Methodes


    public String doListCommentRoute() throws  NotFoundException{
       /* commentList = commentManager.getListComment();*/
        route = routeManager.getRouteById(id);
        if(route!=null){
            System.out.println("tried to get the comment liste");
            commentListRoute = commentManager.getListCommentFromRoute(route.getId());
            System.out.println(route.getName());
        }
        if(commentListRoute.size() > 0) {
            System.out.println("text from comment: " + commentListRoute.get(0).getText());
        }
        return ActionSupport.SUCCESS;
    }
    public String doListCommentSpot() throws  NotFoundException{
        /* commentList = commentManager.getListComment();*/
        spot = spotManager.getSpotById(id);
        if(spot!=null){
            System.out.println("tried to get the comment liste");
            commentListSpot = commentManager.getListCommentFromSpot(spot.getId());
            System.out.println(spot.getName());
        }
        if(commentListSpot.size() > 0) {
            System.out.println("text from comment: " + commentListSpot.get(0).getText());
        }
        return ActionSupport.SUCCESS;
    }
    public String doCreateCommentRoute() throws NotFoundException {
        String vResult= ActionSupport.INPUT;
        System.out.println("je suis suppose etre la");

        if(comment!=null){
            System.out.println("comment text: "+comment.getText());
            System.out.println("comment member: "+comment.getMemberComment().getLogin());
            System.out.println("comment route: "+comment.getRoute().getName());
            commentManager.addComment(comment);
            vResult = ActionSupport.SUCCESS;
        }else{
        }
        return vResult;
    }

    public String doCreateCommentSpot() throws NotFoundException {
        String vResult= ActionSupport.INPUT;
        System.out.println("je suis suppose etre la");

        if(comment!=null){
            System.out.println("comment text: "+comment.getText());
            System.out.println("comment member: "+comment.getMemberComment().getLogin());
            System.out.println("comment route: "+comment.getSpot().getName());
            commentManager.addComment(comment);
            vResult = ActionSupport.SUCCESS;
        }else{
        }
        return vResult;
    }


    public String doDetail() throws NotFoundException {
        String vResult = ActionSupport.SUCCESS;
        try {
            route = routeManager.getRouteById(id);
            System.out.println("getting route: " + route.getName());
        }catch(NotFoundException e){
            System.err.println("Route not found: "+e.getMessage());
        }
        if (this.hasErrors()) {
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    public String doEdit() {
        String vResult = ActionSupport.SUCCESS;

        if (this.hasErrors()) {
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }
    public String doUpdate() {
        String vResult = ActionSupport.INPUT;


        if (this.hasErrors()) {
            System.out.println("Spot is null");
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    public String doDelete() throws  NotFoundException{

        return ActionSupport.SUCCESS;
    }


    // Getters and Setters


    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Comment> getCommentListRoute() {
        return commentListRoute;
    }

    public void setCommentListRoute(List<Comment> commentList) {
        this.commentListRoute = commentListRoute;
    }

    public List<Comment> getCommentListSpot() {
        return commentListSpot;
    }

    public void setCommentListSpot(List<Comment> commentListSpot) {
        this.commentListSpot = commentListSpot;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }
}
