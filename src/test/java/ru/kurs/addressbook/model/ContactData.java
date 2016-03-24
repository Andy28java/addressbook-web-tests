package ru.kurs.addressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String company;
    private PhoneInfo phones = new PhoneInfo();
    private String address;
    private EmailInfo emails = new EmailInfo();
    private String allDetails;

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

    public String getEmail2() { return emails.s;}

    public ContactData withEmail2(String email2) {
        emails = EmailInfo.getInfo(emails.f, email2, emails.t);
        return this;
    }
    public String getEmail() { return emails.f;}

    public ContactData withEmail(String email) {
        emails = EmailInfo.getInfo(email, emails.s, emails.t);
        return this;
    }
    public String getEmail3() { return emails.t;}
    public ContactData withEmail3(String email3) {
        emails = EmailInfo.getInfo(emails.f, emails.s, email3);
        return this;
    }
    /*public String getEmail2() {
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
    }*/

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
        if ((phones != null) ? !phones.equals(that.phones) : that.phones !=null) return false;
        if ((emails != null) ? !emails.equals(that.emails) : that.emails !=null) {
            return false;
        }
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
    private static class EmailInfo {
        String f, s, t;
        String emails;

        private void updateEmails() {
            emails = f;

            if (f != null && !f.isEmpty()) {
                emails += "\n";
            }

            emails += s;

            if (s != null && !s.isEmpty()) {
                emails += "\n";
            }

            emails += t;
        }

        public String toString() {
            return emails;
        }

        public static EmailInfo getInfo(String f, String s, String t) {
            EmailInfo e = new EmailInfo();
            e.f = f == null ? "" : f;
            e.s = s == null ? "" : s;
            e.t = t == null ? "" : t;

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
            phones = h;

            if (h !=null && !h.isEmpty()) {
                phones += "\n";
            }

            phones += m;

            if (m != null && !m.isEmpty()) {
                phones += "\n";
            }

            phones += w;
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
