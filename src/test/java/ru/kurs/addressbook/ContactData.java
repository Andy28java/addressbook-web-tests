package ru.kurs.addressbook;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String homephone;
    private final String email2;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String homephone, String email2) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.homephone = homephone;
        this.email2 = email2;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail2() {
        return email2;
    }
}
