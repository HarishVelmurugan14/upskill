package javaLearn.classConcept.ArrayList;

import javaLearn.classConcept.Contact;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MobilePhone {

    String myNumber;
    ArrayList<Contact> myContacts;
    static MobilePhone mp = new MobilePhone("7094214998");

    public static void main(String[] args) {
        mp.printContacts();
        System.out.println(mp.addNewContact(Contact.createContact("Siva", "143")));
        System.out.println(mp.addNewContact(Contact.createContact("Praveen", "144")));
        System.out.println(mp.findContact("Praveen"));
        mp.printContacts();
        mp.updateContact(new Contact("Siva", "143"), new Contact("Akthar", "122"));
        mp.printContacts();
        mp.removeContact(new Contact("Akthar", ""));
        mp.printContacts();
        System.out.println(mp.findContact("Praveen"));
        // System.out.println(mp.findContact(new Contact("Siva", "12")));
    }

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact) == -1) {
            myContacts.add(contact);
            return true;
        }
        return false;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int position = findContact(oldContact);
        if (position >= 0) {
            myContacts.set(position, newContact);
            return true;
        }
        return false;
    }

    public boolean removeContact(Contact contact) {
        int position = findContact(contact);
        if (position >= 0) {
            myContacts.remove(position);
            return true;
        }
        return false;
    }

    private int findContact(Contact contact) {
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equalsIgnoreCase(contact.getName())) {
                return i;
            }
        }
        return -1;
    }

    private int findContact(String contactName) {
        return findContact(new Contact(contactName, ""));
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return myContacts.get(position);
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            System.out.println(i + ". "+ myContacts.get(i).getName() + " -> " + myContacts.get(i).getPhoneNumber());
        }
    }
}
