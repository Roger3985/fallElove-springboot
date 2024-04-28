package com.firesnoopy.studioorder.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.firesnoopy.studioinfo.entity.StudioInfo;
import com.firesnoopy.studiotimebooking.entity.StudioTimeBooking;
import com.ren.administrator.entity.Administrator;
import com.roger.member.entity.Member;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "studioorder")
public class StudioOrder {
    @Id
    @Column(name = "sordno")
    private Integer sOrdNo;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "memno", referencedColumnName = "memno")
    private Member member;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "sno", referencedColumnName = "sno")
    private StudioInfo studioInfo;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "admno", referencedColumnName = "admno")
    private Administrator administrator;
    @Column(name = "bookeddate")
    private Date bookedDate;
    @Column(name = "bookedtimemorning")
    private Byte bookedTimeMorning;
    @Column(name = "bookedtimeafternoon")
    private Byte bookedTimeAfternoon;
    @Column(name = "bookedtimenight")
    private Byte bookedTimeNight;
    @Column(name = "sordtime")
    private Timestamp sOrdTime;
    @Column(name = "sordstat")
    private Byte sOrdStat;
    @Column(name = "sttlprice")
    private BigDecimal sTtlPrice;
    @Column(name = "sdepprice")
    private BigDecimal sDepPrice;
    @Column(name = "sbyrname")
    private String sByrName;
    @Column(name = "sbyrphone")
    private String sByrPhone;
    @Column(name = "sbyremail")
    private String sByrEmail;
    @Column(name = "spaymethod")
    private Byte sPayMethod;
    @Column(name = "spaystat")
    private Byte sPayStat;
    @Column(name = "checkinstat")
    private Byte checkInStat;
    @Column(name = "sreturnmark")
    private String sReturnMark;
    @Column(name = "scompensation")
    private BigDecimal sCompensation;
    @JsonBackReference
    @OneToMany(mappedBy = "studioOrder", cascade = CascadeType.ALL)
    private Set<StudioTimeBooking> studioTimeBookings;

    public Integer getsOrdNo() {
        return sOrdNo;
    }

    public void setsOrdNo(Integer sOrdNo) {
        this.sOrdNo = sOrdNo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public StudioInfo getStudioInfo() {
        return studioInfo;
    }

    public void setStudioInfo(StudioInfo studioInfo) {
        this.studioInfo = studioInfo;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Byte getBookedTimeMorning() {
        return bookedTimeMorning;
    }

    public void setBookedTimeMorning(Byte bookedTimeMorning) {
        this.bookedTimeMorning = bookedTimeMorning;
    }

    public Byte getBookedTimeAfternoon() {
        return bookedTimeAfternoon;
    }

    public void setBookedTimeAfternoon(Byte bookedTimeAfternoon) {
        this.bookedTimeAfternoon = bookedTimeAfternoon;
    }

    public Byte getBookedTimeNight() {
        return bookedTimeNight;
    }

    public void setBookedTimeNight(Byte bookedTimeNight) {
        this.bookedTimeNight = bookedTimeNight;
    }

    public Timestamp getsOrdTime() {
        return sOrdTime;
    }

    public void setsOrdTime(Timestamp sOrdTime) {
        this.sOrdTime = sOrdTime;
    }

    public Byte getsOrdStat() {
        return sOrdStat;
    }

    public void setsOrdStat(Byte sOrdStat) {
        this.sOrdStat = sOrdStat;
    }

    public BigDecimal getsTtlPrice() {
        return sTtlPrice;
    }

    public void setsTtlPrice(BigDecimal sTtlPrice) {
        this.sTtlPrice = sTtlPrice;
    }

    public BigDecimal getsDepPrice() {
        return sDepPrice;
    }

    public void setsDepPrice(BigDecimal sDepPrice) {
        this.sDepPrice = sDepPrice;
    }

    public String getsByrName() {
        return sByrName;
    }

    public void setsByrName(String sByrName) {
        this.sByrName = sByrName;
    }

    public String getsByrPhone() {
        return sByrPhone;
    }

    public void setsByrPhone(String sByrPhone) {
        this.sByrPhone = sByrPhone;
    }

    public String getsByrEmail() {
        return sByrEmail;
    }

    public void setsByrEmail(String sByrEmail) {
        this.sByrEmail = sByrEmail;
    }

    public Byte getsPayMethod() {
        return sPayMethod;
    }

    public void setsPayMethod(Byte sPayMethod) {
        this.sPayMethod = sPayMethod;
    }

    public Byte getsPayStat() {
        return sPayStat;
    }

    public void setsPayStat(Byte sPayStat) {
        this.sPayStat = sPayStat;
    }

    public Byte getCheckInStat() {
        return checkInStat;
    }

    public void setCheckInStat(Byte checkInStat) {
        this.checkInStat = checkInStat;
    }

    public String getsReturnMark() {
        return sReturnMark;
    }

    public void setsReturnMark(String sReturnMark) {
        this.sReturnMark = sReturnMark;
    }

    public BigDecimal getsCompensation() {
        return sCompensation;
    }

    public void setsCompensation(BigDecimal sCompensation) {
        this.sCompensation = sCompensation;
    }

    public Set<StudioTimeBooking> getStudioTimeBookings() {
        return studioTimeBookings;
    }

    public void setStudioTimeBookings(Set<StudioTimeBooking> studioTimeBookings) {
        this.studioTimeBookings = studioTimeBookings;
    }
}
