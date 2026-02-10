package com.formavan.validation;

import java.util.List;
import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T target);
}