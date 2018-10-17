/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapinobelprize;

/**
 *
 * @author bergeron
 */

public class NobelPrizeLaureate
{
    private String firstname;
    private String surname;
    private String category;
    private int year;

    public NobelPrizeLaureate(String firstname, String surname, String category, int year)
    {
        this.firstname = firstname;
        this.surname = surname;
        this.category = category;
        this.year = year;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
    
    


