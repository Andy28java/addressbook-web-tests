package ru.kurs.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String middlename;
    @Expose
    private String lastname;
    private String nickname;
    private String company;
    private String homephone;
    private PhoneInfo phones = new PhoneInfo();
    @Expose
    private String address;
    private EmailInfo emails = new EmailInfo();
    private String allDetails;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private File photo;

    public String getAllDetails() {
        return allDetails;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
     /*   if ((phones != null) ? !phones.equals(that.phones) : that.phones !=null) return false;
        if ((emails != null) ? !emails.equals(that.emails) : that.emails !=null) {
            return false;
        }
      */
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

            return phones != null ? cleaned(phones).equals(cleaned(phoneInfo.phones)) : phoneInfo.phones == null;
        }

        @Override
        public int hashCode() {
            return phones != null ? phones.hashCode() : 0;
        }
    }
}
