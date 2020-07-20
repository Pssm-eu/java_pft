package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getContactFirstName());
        type(By.name("lastname"), contactData.getContactLastName());
        type(By.name("address"), contactData.getContactAddress());
        type(By.name("mobile"), contactData.getContactMobilePhone());
        type(By.name("email"), contactData.getContactEmail());
//        attach(By.name("photo"), contactData.getPhoto());
    }

    public void gotoContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    private void selectGroup(int index) {
        wd.findElement(By.cssSelector("select[name='to_group'] option[value='" + index + "'")).click();
    }

    public void selectGroupList(int index) {
        wd.findElement(By.cssSelector("select[name='group'] option[value='"+index+"'")).click();
    }


    private void addContactToGroup() {
        click(By.name("add"));
    }

    /*
    public void deleteFromGroup(ContactData deletedContact, GroupData groupBefore) {
        chooseGroupToDeleteById(groupBefore.getId());
        selectContactById(deletedContact.getId());
        deleteContactFromGroup();
    }
    */

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }


    public void deleteContactFromGroup(Contacts before) {
        selectContact(before.size() - 1);
        click(By.name("remove"));
    }

    public void removeContactFromGroup(Contacts before, int beforeGroups) {
        selectGroupList(beforeGroups);
        deleteContactFromGroup(before);
        returnToHomePage();
    }


    private void chooseGroupToDeleteById(int id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
    }

    private void chooseGroupToAddById(int id) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
    }

    public void addToGroup(int beforeGroups) {
        selectGroup(beforeGroups);
        click(By.name("add"));
    }

    public void addContactToGroup(Contacts before, int beforeGroups) {
        selectContact(before.size() - 1);
        addToGroup(beforeGroups);
        returnToHomePage();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        gotoContactCreationPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String contactFirstName = cells.get(2).getText();
            String contactLastName = cells.get(1).getText();
            // String[] phones = cells.get(5).getText().split("\n");
            String allAddress = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData()
                    .withId(id).withContactFirstName(contactFirstName).withContactLastName(contactLastName)
                    .withAllAddress(allAddress).withAllPhones(allPhones).withAllEmails(allEmails);
            contacts.add(contact);
        }
        return contacts;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String contactFirstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String contactLastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String contactHomePhone = wd.findElement(By.name("home")).getAttribute("value");
        String contactMobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String contactWorkPhone = wd.findElement(By.name("work")).getAttribute("value");
        String contactAddress = wd.findElement(By.name("address")).getAttribute("value");
        String contactEmail = wd.findElement(By.name("email")).getAttribute("value");
        String contactEmail2 = wd.findElement(By.name("email2")).getAttribute("value");
        String contactEmail3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withContactFirstName(contactFirstName)
                .withContactLastName(contactLastName).withContactHomePhone(contactHomePhone)
                .withContactMobilePhone(contactMobilePhone).withContactWorkPhone(contactWorkPhone)
                .withAllAddress(contactAddress)
                .withContactEmail(contactEmail).withContactEmail2(contactEmail2).withContactEmail3(contactEmail3);
    }

}
