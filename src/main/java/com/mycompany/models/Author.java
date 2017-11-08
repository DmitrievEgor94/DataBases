package com.mycompany.models;

import java.time.LocalDate;
import java.util.Optional;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != this.getClass()) return false;

        Author author = (Author) obj;

        if (!this.name.equals(author.name)) {
            return false;
        }

        if (!this.dayOfBirthday.equals(author.dayOfBirthday)) {
            return false;
        }

        if (this.dayOfDeath == null) {
            if (author.dayOfDeath != null) {
                return false;
            }
        } else {
            if (!this.dayOfDeath.equals(author.dayOfDeath)) {
                return false;
            }
        }

        return this.sex.equals(author.sex);
    }
}
