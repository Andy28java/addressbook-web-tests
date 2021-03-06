package ru.kurs.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "middlename")
    private String middlename;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Transient
    private String nickname;
    @Transient
    private String company;
   // @Transient
    //private String group;
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;
    @Column(name = "work")
    @Type(type = "text")
    private String workphone;
    @Transient
    private PhoneInfo phones = PhoneInfo.getInfo("");
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Transient
    private EmailInfo emails = new EmailInfo();
    @Transient
    private String allDetails;

    public Groups getGroups() {
        return new Groups(groups);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id")
            , inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();
    public ContactData inGroup(GroupData g) {
        groups.add(g);
        return this;
    }

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    /*public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public String getGroup() {
        return group;
    }*/
    public String getAllDetails() {
        return allDetails;
    }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        }
        return null;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withAllDetails(String allDetails) {
        this.allDetails = allDetails;
        return this;
    }
    public String getMobilephone() { return phones.m;}

    public ContactData withMobilephone(String mobilephone) {
        phones = PhoneInfo.getInfo(phones.h, mobilephone, phones.w);
        return this;
    }

    public String getHomephone() { return phones.h;}
    public ContactData withHomephone(String homephone) {
        phones = PhoneInfo.getInfo(homephone, phones.m, phones.h);
        return this;
    }

    public String getWorkphone() { return phones.w;}
    public ContactData withWorkphone(String workphone) {
        phones = PhoneInfo.getInfo(phones.h, phones.m, workphone);
        return this;
    }

    public ContactData  withPhones(String p) {
        phones = PhoneInfo.getInfo(p);
        return this;
    }

    public PhoneInfo getPhones() {
        return phones;
    }

    public void syncPhoneInfoWithDB() {
        phones = PhoneInfo.getInfo(homephone, mobilephone, workphone);
    }

    public ContactData  withEmails(String e) {
        emails = EmailInfo.getInfo(e);
        return this;
    }

    public EmailInfo getEmails() {
        return emails;
    }

    public String getAddress() { return address;}
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail2() { return emails.email[1].raw;}

    public ContactData withEmail2(String email2) {
        emails = EmailInfo.getInfo(emails.email[0].raw, email2, emails.email[2].raw);
        return this;
    }
    public String getEmail() { return emails.email[0].raw;}

    public ContactData withEmail(String email) {
        emails = EmailInfo.getInfo(email, emails.email[1].raw, emails.email[2].raw);
        return this;
    }
    public String getEmail3() { return emails.email[2].raw;}
    public ContactData withEmail3(String email3) {
        emails = EmailInfo.getInfo(emails.email[0].raw, emails.email[1].raw, email3);
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

    public  static class EmailInfo {
        public  static class Email {
            final String raw;
            public final String decorated;

            public Email(String e) {
                if (e == null) {
                    e = "";
                }

                raw = e;

                int i = e.indexOf("@");
                if (i == -1) {
                    decorated = raw;
                } else {
                    String domain = e.substring(i + 1);
                    if (domain.isEmpty()) {
                        decorated = raw;
                    } else {
                        decorated = raw + " (www." + domain + ")";
                    }
                }
            }
        }

        public final Email email[];
        String emails;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EmailInfo emailInfo = (EmailInfo) o;

            return emails != null ? emails.equals(emailInfo.emails) : emailInfo.emails == null;

        }

        @Override
        public int hashCode() {
            return emails != null ? emails.hashCode() : 0;
        }

        protected EmailInfo() {
            email = new Email[3];
            for (int i = 0; i < 3; i++) {
                email[i] = new Email("");
            }

        }
        private void updateEmails() {
            emails = "";
            String prefix = "";
            for (Email e : email) {
                if (!e.raw.isEmpty()) {
                    emails += prefix;
                    emails += e.raw;
                    prefix = "\n";
                }
            }
        }

        public String toString() {
            return emails;
        }

        public static EmailInfo getInfo(String f, String s, String t) {
            EmailInfo e = new EmailInfo();
            e.email[0] = new Email(f);
            e.email[1] = new Email(s);
            e.email[2] = new Email(t);

            e.updateEmails();

            return e;
        }
        public static EmailInfo getInfo(String emails) {
            EmailInfo p = new EmailInfo();

            p.emails = emails;

            return p;
        }
    }
    private static class PhoneInfo {
        String w, m, h;
        String phones;

        private PhoneInfo() {
            w = "";
            m = "";
            h = "";
            phones = "";
        }

        private static String cleaned(String phone){
            return phone.replaceAll("[-() \t]", "");
        }

        private void updatePhones() {
            String[] all = new String[] { h, m, w};

            String prefix = "";
            phones = "";
            for (String p : all) {
                if (p != null && !p.isEmpty()) {
                    phones += prefix;
                    phones += p;
                    prefix = "\n";
                }
            }
        }

        public String toString() {
            return phones;
        }

        public static PhoneInfo getInfo(String h, String m, String w) {
            PhoneInfo p = new PhoneInfo();
            p.w = w == null ? "" : w;
            p.h = h == null ? "" : h;
            p.m = m == null ? "" : m;

            p.updatePhones();

            return p;
        }

        public static PhoneInfo getInfo(String phones) {
            PhoneInfo p = new PhoneInfo();

            p.phones = phones;

            return p;
        }

        public static PhoneInfo getInfoWithDetails(String details) {
            PhoneInfo p = new PhoneInfo();

            String[] pp = details.split("\n");

            for (String ph : pp) {
                if (ph.startsWith("H: ")) {
                    p.h = ph.substring(3);
                } else if (ph.startsWith("M: ")) {
                    p.m = ph.substring(3);
                } else if (ph.startsWith("W: ")) {
                    p.w = ph.substring(3);
                } else {
                    throw new RuntimeException("Unknown phone: " + ph);
                }
            }

            p.updatePhones();

            return p;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PhoneInfo phoneInfo = (PhoneInfo) o;

            return phones != null ? phones.equals(phoneInfo.phones) : phoneInfo.phones == null;
        }

        @Override
        public int hashCode() {
            return phones != null ? phones.hashCode() : 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null)
            return false;
        //if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null)
            return false;
        /*
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null)
            return false;
        if (company != null ? !company.equals(that.company) : that.company != null)
            return false;
        if (phones != null ? !phones.equals(that.phones) : that.phones != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null)
            return false;
        if (emails != null ? !emails.equals(that.emails) : that.emails != null)
            return false;
        if (photo != null ? that.photo == null : that.photo != null)  {
            return  false;
        }
        */
        return true;
    }

    @Override
    public int hashCode() {

        int result = id;

        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        //result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        //result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        //result = 31 * result + (company != null ? company.hashCode() : 0);
        /*
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (emails != null ? emails.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        */
        return result;

        //return super.hashCode();

    }
}
