package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("TestFirstName").withLastName("TestLastName")
              .withAddress("TestAddress").withMobilePhone("12345").withHomePhone("54321").withWorkPhone("87655")
              .withEmail("testmail@gmail.com").withEmail2("testmail2@gmail.com").withEmail3("testmail3@gmail.com"));
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().mainPage();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
