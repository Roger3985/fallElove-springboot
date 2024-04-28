package com.firesnoopy.studiotimebooking.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.firesnoopy.studioinfo.entity.StudioInfo;
import com.firesnoopy.studioorder.entity.StudioOrder;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "studiotimebooking")
public class StudioTimeBooking {
    @Id
    @Column(name = "stimeno")
    private Integer sTimeNo;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "sordno", referencedColumnName = "sordno")
    private StudioOrder studioOrder;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "sno", referencedColumnName = "sno")
    private StudioInfo studioInfo;
    @Column(name = "closedate")
    private Date closeDate;
    @Column(name = "closetimemorning")
    private Byte closeTimeMorning;
    @Column(name = "closetimeafternoon")
    private Byte closeTimeAfternoon;
    @Column(name = "closetimenight")
    private Byte closeTimeNight;

    public Integer getsTimeNo() {
        return sTimeNo;
    }

    public void setsTimeNo(Integer sTimeNo) {
        this.sTimeNo = sTimeNo;
    }

    public StudioOrder getStudioOrder() {
        return studioOrder;
    }

    public void setStudioOrder(StudioOrder studioOrder) {
        this.studioOrder = studioOrder;
    }

    public StudioInfo getStudioInfo() {
        return studioInfo;
    }

    public void setStudioInfo(StudioInfo studioInfo) {
        this.studioInfo = studioInfo;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Byte getCloseTimeMorning() {
        return closeTimeMorning;
    }

    public void setCloseTimeMorning(Byte closeTimeMorning) {
        this.closeTimeMorning = closeTimeMorning;
    }

    public Byte getCloseTimeAfternoon() {
        return closeTimeAfternoon;
    }

    public void setCloseTimeAfternoon(Byte closeTimeAfternoon) {
        this.closeTimeAfternoon = closeTimeAfternoon;
    }

    public Byte getCloseTimeNight() {
        return closeTimeNight;
    }

    public void setCloseTimeNight(Byte closeTimeNight) {
        this.closeTimeNight = closeTimeNight;
    }
}
