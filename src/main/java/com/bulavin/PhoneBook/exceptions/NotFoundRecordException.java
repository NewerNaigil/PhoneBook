package com.bulavin.PhoneBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such record")
public class NotFoundRecordException extends RuntimeException {
}
