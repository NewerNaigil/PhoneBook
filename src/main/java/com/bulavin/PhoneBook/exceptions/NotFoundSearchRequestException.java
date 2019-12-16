package com.bulavin.PhoneBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Search request not found")
public class NotFoundSearchRequestException extends RuntimeException {
}
