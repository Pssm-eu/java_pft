package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactExtendedInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().mainPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("t").withLastName("tr")
              .withAddress("tre").withMobilePhone("1").withHomePhone("2").withWorkPhone("3")
              .withEmail("qwert").withEmail2("wef").withEmail3("efq"));
    }
  }

  @Test
  public void testContactExtendedInfo() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromViewForm = app.contact().infoFromViewForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(cleanedViewInfo(contactInfoFromViewForm.getAllMainInfo()), equalTo(mergeMainInfo(contactInfoFromEditForm)));
  }

  private String mergeMainInfo(ContactData contact) {
    return Arrays.asList(contact.getFirstName(), contact.getLastName(), contact.getAddress()
            , contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()
            , contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactExtendedInfoTests::cleanedEditInfo)
            .collect(Collectors.joining(""));
  }

  public static String cleanedEditInfo(String phone) {
    return phone.replaceAll("\\s", "");
  }

  public static String cleanedViewInfo(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("\n","")
            .replaceAll("[:HMW]","");
  }
}