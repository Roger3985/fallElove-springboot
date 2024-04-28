package com.firesnoopy.studioinfo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.firesnoopy.studioorder.entity.StudioOrder;
import com.firesnoopy.studiotimebooking.entity.StudioTimeBooking;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "studioinfo")
public class StudioInfo {
    @Id
    @Column(name = "sno")
    private Integer sNo;
    @Column(name = "sname")
    private String sName;
    @Column(name = "sinfo")
    private String sInfo;
    @Column(name = "sprice")
    private BigDecimal sPrice;
    @JsonBackReference
    @OneToMany(mappedBy = "studioInfo", cascade = CascadeType.ALL)
    private Set<StudioOrder> studioOrders;
    @JsonBackReference
    @OneToMany(mappedBy = "studioInfo", cascade = CascadeType.ALL)
    private Set<StudioTimeBooking> studioTimeBookings;

    public Integer getsNo() {
        return sNo;
    }

    public void setsNo(Integer sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }

    public BigDecimal getsPrice() {
        return sPrice;
    }

    public void setsPrice(BigDecimal sPrice) {
        this.sPrice = sPrice;
    }

    public Set<StudioOrder> getStudioOrders() {
        return studioOrders;
    }

    public void setStudioOrders(Set<StudioOrder> studioOrders) {
        this.studioOrders = studioOrders;
    }

    public Set<StudioTimeBooking> getStudioTimeBookings() {
        return studioTimeBookings;
    }

    public void setStudioTimeBookings(Set<StudioTimeBooking> studioTimeBookings) {
        this.studioTimeBookings = studioTimeBookings;
    }
}
