package org.example.learn;

class MyClass implements InterfaceA, InterfaceB {
    @Override
    public void commonMethod() {
        // resolving ambiguity by providing my own custom implementation of that conflicting method
        System.out.println("From MyClass");
        // or I can also explicitly call a specific interface's method:
        // InterfaceA.super.commonMethod();
    }
}
