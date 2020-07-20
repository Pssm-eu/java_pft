package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().ContactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                .withContactFirstName("ilya_test").withContactLastName("ilya_test")
                .withContactHomePhone("111").withContactMobilePhone("222").withContactWorkPhone("333")
                .withContactEmail("ilya@test").withContactEmail2("vasya@yandex.ru").withContactEmail3("alex@mail.ru")
                .withContactAddress("Russia, Ryazan"));
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().gotoToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAllAddress())
            .stream().filter((s) -> s != null && ! s.equals(""))
            .collect(Collectors.joining("\n"));
    }
}
