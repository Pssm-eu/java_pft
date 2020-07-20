package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertTrue;

public class DeleteContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
            app.goTo().ContactPage();
            app.contact().create(new ContactData()
                    .withContactFirstName("firstname_test")
                    .withContactLastName("lastname_test"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData()
                    .withName("test2'"));
        }

        int beforeCount = app.db().groups().iterator().next().getContacts().size();
        GroupData groupAdd = app.db().groups().iterator().next();
        if(beforeCount == 0){
            Contacts before = app.db().contacts();
            app.contact().addContactToGroup(before, groupAdd.getId());

        }
    }

    @Test
    public void testDeleteContactFromGroup() {


        /*
        Groups groupsBefore = app.db().contacts().iterator().next().getGroups();
        Contacts before = app.db().contacts();
        GroupData groupOut = app.db().groups().iterator().next();

        app.goTo().gotoToHomePage();
        app.contact().removeContactFromGroup(before, groupOut.getId());

        Groups groupsAfter = app.db().contacts().iterator().next().getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.without(groupOut)));
        */
        /*
        app.goTo().gotoToHomePage();
        Contacts before = app.db().contactsInGroup();
        ContactData deletedContact = before.iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.contact().deleteFromGroup(deletedContact, group);
        Contacts after = app.db().contacts();
        assertTrue(after.iterator().next().getGroups().isEmpty());
           */
    }
}
