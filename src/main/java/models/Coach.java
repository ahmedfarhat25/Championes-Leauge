package models;
import enums.AccLevel;

public class Coach extends Person {
    private AccLevel accreditationLevel;
    private int experience;

    public Coach(String name, String address, AccLevel accreditationLevel, int experience) {
        super(name, address);
        this.accreditationLevel = accreditationLevel;
        this.experience = experience;
    }

    public AccLevel getAccreditationLevel() {
        return accreditationLevel;
    }

    public int getExperience() {
        return experience;
    }
}