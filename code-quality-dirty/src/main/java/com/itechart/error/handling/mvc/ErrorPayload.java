package com.itechart.error.handling.mvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorPayload {

    String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<FieldError> fieldErrors;

    public ErrorPayload(String message) {
        this.message = message;
    }

    public static ErrorPayload fromConstraintViolations(Set<ConstraintViolation<?>> violations) {
        ErrorPayload errorPayload = new ErrorPayload("Params are invalid");
        errorPayload.fieldErrors = violations.stream().map((violation) -> {
            Iterator<Path.Node> pathIterator = violation.getPropertyPath().iterator();

            String property;
            String name;
            for(property = ""; pathIterator.hasNext(); property = name == null ? "root" : name) {
                name = pathIterator.next().getName();
            }

            Object invalidValue = Objects.equals(violation.getInvalidValue(), violation.getLeafBean()) ? "*" : violation.getInvalidValue();
            return new FieldError(violation.getLeafBean().getClass().getSimpleName(), property, invalidValue, false, null, null, violation.getMessage());
        }).collect(Collectors.toList());
        return errorPayload;
    }
}
