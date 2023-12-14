package com.example;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setName(" ");  // Invalid name
        user.setAge(-1);    // Invalid age

        ValidationProcessor processor = new ValidationProcessor();
        try {
            processor.validate(user);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}
