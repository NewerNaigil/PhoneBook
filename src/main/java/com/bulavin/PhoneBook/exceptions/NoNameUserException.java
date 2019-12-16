package com.bulavin.PhoneBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User has no name")
public class NoNameUserException extends RuntimeException{
}
