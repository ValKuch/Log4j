package org.JavaHW.framework.data;


public class Repository {

    private Repository() {
    }

    public static User getDefault() {
        return getValidUser();
    }

    public static User getValidUser() {
        return new User("valerie.bucky69@gmail.com", "123456qQ#", "Valeriia");
    }

    public static User getInvalidUser() {
        return new User("anotheruser@greencity.com", "anotherpassword", " ");
    }

    public static User getInvalidEmails1() {
        return new User("samplestesgreencity.com", "uT346^^^erw", " ");
    }

    public static User getInvalidEmails2() {
        return new User("@email.com", "uT346^^^erw", " ");
    }

    public static User getInvalidEmails3() {
        return new User("some_email", "uT346^^^erw", " ");
    }

    public static User getEmptyPass() {
        return new User("samplestes@greencity.com", " ", " ");
    }

    public static User getInvalidPass1() {
        return new User("samplestes@greencity.com", "tfcyuhgujhikj", " ");
    }

    public static User getInvalidPass2() {
        return new User("samplestes@greencity.com", "12345678", " ");
    }

    public static User getInvalidPass3() {
        return new User("samplestes@greencity.com", "#########", " ");
    }

    public static User getPassReset1() {
        return new User("valerie.bucky69@gmail.com", " ", " ");
    }

    public static User getPassReset2() {
        return new User("samplestes@greencity.com", " ", " ");
    }
}

