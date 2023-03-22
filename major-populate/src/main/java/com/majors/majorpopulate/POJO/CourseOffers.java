package com.majors.majorpopulate.POJO;

public class CourseOffers {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    private int Id;
    private String Title;
    private String Section;
    private String Days;
    private String Term;

    public CourseOffers(int id, String title, String section, String days, String term, String termDate, String time,
            String location, String building, String room, String type) {
        Id = id;
        Title = title;
        Section = section;
        Days = days;
        Term = term;
        TermDate = termDate;
        Time = time;
        Location = location;
        Building = building;
        Room = room;
        Type = type;
    }

    public CourseOffers() {

    }

    private String TermDate;
    private String Time;
    private String Location;
    private String Building;
    private String Room;
    private String Type;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getTermDate() {
        return TermDate;
    }

    public void setTermDate(String termDate) {
        TermDate = termDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "CourseOffers{" +
                "Title='" + Title + '\'' +
                ", Section='" + Section + '\'' +
                ", Days='" + Days + '\'' +
                ", Term='" + Term + '\'' +
                ", TermDate='" + TermDate + '\'' +
                ", Time='" + Time + '\'' +
                ", Location='" + Location + '\'' +
                ", Building='" + Building + '\'' +
                ", Room='" + Room + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}