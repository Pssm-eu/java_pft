package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import ru.stqa.pft.addressbook.tests.ContactDeletionTests;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String contactFirstName;
    @Expose
    @Column(name = "lastname")
    private String contactLastName;
    /*
    @Transient
    private String contactGroup;
    */
    @Column(name = "address")
    @Type(type = "text")
    private String contactAddress;
    @Column(name = "email")
    @Type(type = "text")
    private String contactEmail;
    @Column(name = "email2")
    @Type(type = "text")
    private String contactEmail2;
    @Column(name = "email3")
    @Type(type = "text")
    private String contactEmail3;
    @Column(name = "home")
    @Type(type = "text")
    private String contactHomePhone;
    @Column(name = "mobile")
    @Type(type = "text")
    private String contactMobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String contactWorkPhone;
    @Transient
    private String allPhones;
    @Transient
    private String allAddress;
    @Transient
    private String allEmails;
    @Transient
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getContactEmail2() {
        return contactEmail2;
    }

    public ContactData withContactEmail2(String contactEmail2) {
        this.contactEmail2 = contactEmail2;
        return this;
    }

    public String getContactEmail3() {
        return contactEmail3;
    }

    public ContactData withContactEmail3(String contactEmail3) {
        this.contactEmail3 = contactEmail3;
        return this;
    }

    public String getAllAddress() {
        return allAddress;
    }

    public ContactData withAllAddress(String allAddress) {
        this.allAddress = allAddress;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
        return this;
    }

    public ContactData withContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
        return this;
    }

    public ContactData withContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }
    /*
    public String getGroup() {
        return contactGroup;
    }

    public ContactData withContactGroup(String contactGroup) {
        this.contactGroup = contactGroup;
        return this;
    }
    */

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public ContactData withContactMobilePhone(String contactMobilePhone) {
        this.contactMobilePhone = contactMobilePhone;
        return this;
    }

    public ContactData withContactHomePhone(String contactHomePhone) {
        this.contactHomePhone = contactHomePhone;
        return this;
    }

    public ContactData withContactWorkPhone(String contactWorkPhone) {
        this.contactWorkPhone = contactWorkPhone;
        return this;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public String getContactMobilePhone() {
        return contactMobilePhone;
    }

    public String getContactWorkPhone() {
        return contactWorkPhone;
    }

    public String getContactHomePhone() {
        return contactHomePhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (contactFirstName != null ? !contactFirstName.equals(that.contactFirstName) : that.contactFirstName != null)
            return false;
        return contactLastName != null ? contactLastName.equals(that.contactLastName) : that.contactLastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contactFirstName != null ? contactFirstName.hashCode() : 0);
        result = 31 * result + (contactLastName != null ? contactLastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", contactFirstName='" + contactFirstName + '\'' +
                ", contactLastName='" + contactLastName + '\'' +
                '}';
    }

}
