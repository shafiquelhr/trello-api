package org.example.learn;

interface InterfaceB {
    default void commonMethod() {
        System.out.println("From InterfaceB");
    }
}
