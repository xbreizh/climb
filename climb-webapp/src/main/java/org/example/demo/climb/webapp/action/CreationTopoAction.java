package org.example.demo.climb.webapp.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.example.demo.climb.business.contract.BookingManager;
import org.example.demo.climb.business.contract.CountryManager;
import org.example.demo.climb.business.contract.MemberManager;
import org.example.demo.climb.business.contract.TopoManager;
import org.example.demo.climb.model.bean.Booking;
import org.example.demo.climb.model.bean.Country;
import org.example.demo.climb.model.bean.Topo;
import org.example.demo.climb.model.exception.NotFoundException;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CreationTopoAction extends LoginAction implements SessionAware {


    private Topo topo;
    private List<Topo> topoList;
    List<Integer> yearList = new ArrayList<>();
    private int id;
    private HashMap<Integer, String> countryList= new HashMap<>();
    private Date beginBook;
    private Date endBook;
    private int memberId;
    Booking booking;



    @Inject
    private TopoManager topoManager;
    @Inject
    private CountryManager countryManager;
    @Inject
    private MemberManager memberManager;

    @Inject
    private BookingManager bookingManager;

    // Methods
    public String doCreateTopo() {
        System.out.println("trying to create a topo");
        String vResult= ActionSupport.INPUT;
        initCountryList();
        for(int i = 1900;i<2222;i++){
            yearList.add(i);
        }
        if(topo!=null){
            if(checkTopoForm(topo)){
                System.out.println("topo as such: "+topo);
                topoManager.addTopo(topo);
                vResult = ActionSupport.SUCCESS;
            }
        }
        return vResult;
    }

    /*READ ONE*/
    public String doDetail() throws NotFoundException {
        String vResult = ActionSupport.SUCCESS;
        topo = topoManager.getTopo(id);

        if (this.hasErrors()) {
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    public boolean checkTopoForm(Topo topo){
        int i=0;
        // Check Name
        if(topo.getName().length() < 2 || topo.getName().length() >50){
            this.addFieldError("topo.name", "name must be between 2-50");
            i++;
        }
        // Check Edition
        if(topo.getEdition().length() < 2 || topo.getEdition().length() > 50){
            this.addFieldError("topo.edition", "edition must be between 2-50");
            i++;
        }
        // Check Author
        if(topo.getAuthor().length() < 2 || topo.getAuthor().length() > 50){
            this.addFieldError("topo.author", "author must be between 2-50");
            i++;
        }
        // Check Published Year
        if(topo.getPublishedYear() < 1700 || topo.getPublishedYear() > 2222){
            this.addFieldError("topo.name", "Year must be between 1900-today");
            i++;
        }
        // Check Description
        if(topo.getDescription().length() < 3 || topo.getDescription().length() > 350){
            this.addFieldError("topo.description", "Description must be between 2-50");
            i++;
        }
        // Check Keywords
        if(topo.getKeywords().length() < 2 || topo.getKeywords().length() >50){
            this.addFieldError("topo.keywords", "Keywords must be between 3-350");
            i++;
        }

        // Check Owner
        System.out.println("id "+getSession().get("id"));
        System.out.println("name"+getSession().get("name"));
        System.out.println("values: "+getSession().values().toString());

        System.out.println("name: "+getSession().get("name"));
        System.out.println("id: "+getSession().get("id"));
        if(i>0){
            return false;
        }else{
            return true;
        }

    }

    public String doList() {
        topoList=topoManager.getListTopo();
        System.out.println("trying to get topoList");
        System.out.println("size: " + topoList.size());
        return ActionSupport.SUCCESS;
    }

    // BOOKING
    public String doBooking(){
        int error=0;
        String vResult = ActionSupport.INPUT;
        if(beginBook == null || endBook == null){
            this.addActionError("You must fill out both dates to book");
            error++;
        }else {
            if (beginBook.after(endBook) || beginBook.equals(endBook)) {
                System.out.println("end date must be after begin date");
                this.addFieldError("endBook", "end date must be after begin date");
                error++;
            }
            if (beginBook.before(new Date())) {
                this.addFieldError("beginBook", "start date should be at least tomorrow");
                System.out.println("start date should be at least tomorrow");
                error++;
            }
            if (error == 0) {

                booking.setBookingDate(beginBook);
                booking.setReturnDate(endBook);

                bookingManager.addBooking(booking);

                vResult = ActionSupport.SUCCESS;
            }
        }
        return vResult;
    }

    public String doValidateBooking(){

        return ActionSupport.SUCCESS;
    }

    private void initCountryList() {
        for (Country c: countryManager.getListCountry()
        ) {
            countryList.put(c.getId(), c.getName());
        }
    }

    // Getters and Setters

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }
    public List<Topo> getTopoList() {
        return topoList;
    }

    public void setTopoList(List<Topo> topoList) {
        this.topoList = topoList;
    }
    public List<Integer> getYearList() {
        return yearList;
    }

    public void setYearList(List<Integer> yearList) {
        this.yearList = yearList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, String> getCountryList() {
        return countryList;
    }

    public void setCountryList(HashMap<Integer, String> countryList) {
        this.countryList = countryList;
    }

    public Date getEndBook() {
        return endBook;
    }

    public void setEndBook(Date endBook) {
        this.endBook = endBook;
    }

    public Date getBeginBook() {
        return beginBook;
    }

    public void setBeginBook(Date beginBook) {
        this.beginBook = beginBook;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
