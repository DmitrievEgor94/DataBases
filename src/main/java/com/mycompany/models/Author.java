package com.mycompany.models;

import java.time.LocalDate;
import java.util.Objects;

public class Author {

    private int id;
    private String name;
    private LocalDate dayOfBirthday;
    private LocalDate dayOfDeath;
    private Sex sex;

    public enum Sex {MALE, FEMALE, male, female}

    public Author() {
    }

    public Author(String name, LocalDate dayOfBirthday, LocalDate dayOfDeath, Sex sex) {
        this.name = name;
        this.dayOfBirthday = dayOfBirthday;
        this.dayOfDeath = dayOfDeath;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDayOfBirthday() {
        return dayOfBirthday;
    }

    public LocalDate getDayOfDeath() {
        return dayOfDeath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayOfBirthday(LocalDate dayOfBirthday) {
        this.dayOfBirthday = dayOfBirthday;
    }

    public void setDayOfDeath(LocalDate dayOfDeath) {
        this.dayOfDeath = dayOfDeath;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dayOfBirthday, dayOfDeath, sex);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != this.getClass()) return false;

        Author author = (Author) obj;

        return ((this.name == null) ? (author.name == null) : (this.name.equals(author.name)))
                && ((this.dayOfBirthday == null) ? (author.dayOfBirthday == null) : (this.dayOfBirthday.equals(author.dayOfBirthday)))
                && ((this.dayOfDeath == null) ? (author.dayOfDeath == null) : (this.dayOfDeath.equals(author.dayOfDeath)))
                && ((this.sex == null) ? (author.sex == null) : (this.sex.equals(author.sex)));

    }
}
