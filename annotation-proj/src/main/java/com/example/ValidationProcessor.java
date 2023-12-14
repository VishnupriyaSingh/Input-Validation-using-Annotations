package com.example;

import java.lang.reflect.Field;

public class ValidationProcessor {

    public void validate(User obj) throws ValidationException {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                // Check @NotBlank
                if (field.isAnnotationPresent(NotBlank.class)) {
                    Object value = field.get(obj);
                    if (value == null || value.toString().trim().isEmpty()) {
                        throw new ValidationException("Field " + field.getName() + " must not be blank");
                    }
                }

                // Check @Positive
                if (field.isAnnotationPresent(Positive.class)) {
                    Object value = field.get(obj);
                    if (value instanceof Number && ((Number) value).doubleValue() <= 0) {
                        throw new ValidationException("Field " + field.getName() + " must be positive");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
