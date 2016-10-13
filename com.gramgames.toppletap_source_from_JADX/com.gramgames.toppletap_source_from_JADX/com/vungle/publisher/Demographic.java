package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class Demographic {
    private Integer a;
    private Gender b;

    /* compiled from: vungle */
    public enum Gender {
        female,
        male
    }

    @Inject
    Demographic() {
    }

    public Integer getAge() {
        return this.a;
    }

    public void setAge(Integer age) {
        this.a = age;
    }

    public Gender getGender() {
        return this.b;
    }

    public void setGender(Gender gender) {
        this.b = gender;
    }

    public boolean isEmpty() {
        return this.a == null && this.b == null;
    }
}
