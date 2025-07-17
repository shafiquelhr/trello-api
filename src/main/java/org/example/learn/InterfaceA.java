package org.example.learn;

interface InterfaceA {
    default void commonMethod() {
        System.out.println("From InterfaceA");
    }
}
