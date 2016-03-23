package ru.kurs.addressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String company;
    private String homephone;
    private String mobilephone;
    private String workphone;
    private String address;
    private String email;
    private String email2;
    private String email3;
    private String allDetails;

    public String getAllDetails() {
        return allDetails;
    }

    public ContactData withAllDetails(String allDetails) {
        this.allDetails = allDetails;
        return this;
    }
    public String getMobilephone() { return mobilephone;}
    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public String getHomephone() { return homephone;}
    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public String getWorkphone() { return workphone;}
    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public String getAddress() { return address;}
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail2() {
        return email2;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail3() {
        return email3; }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

   /* public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String homephone, String email2) {
        this.id = 0;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.homephone = homephone;
        this.email2 = email2;
    }

    public ContactData(int id, String firstname, String middlename, String lastname, String nickname, String company, String homephone, String email2) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.homephone = homephone;
        this.email2 = email2;
    }
*/
    public String getFirstname() {
        return firstname;
    }

    public int getId() {
        return id;
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

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

       @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
