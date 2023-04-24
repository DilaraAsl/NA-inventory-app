package edu.na.exceptions;

import edu.na.annotation.InventoryAppExceptionMessage;
import edu.na.dto.DefaultExceptionMessageDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Throwable.class})
    public String genericException(Throwable exception, HandlerMethod handlerMethod, Model model) {
        exception.printStackTrace();
        String message = "Something went wrong!";
        Optional<DefaultExceptionMessageDto> defaultMessage = getMessageFromAnnotation(handlerMethod.getMethod());
        if (defaultMessage.isPresent()) {
            message = defaultMessage.get().getMessage();
        } else if (exception.getMessage() != null) {
            message = exception.getMessage();
        }
        model.addAttribute("message", message);
        return "error";
    }

    private Optional<DefaultExceptionMessageDto> getMessageFromAnnotation(Method method) {
        InventoryAppExceptionMessage defaultExceptionMessage = method
                .getAnnotation(InventoryAppExceptionMessage.class);
        if (defaultExceptionMessage != null) {
            DefaultExceptionMessageDto defaultExceptionMessageDto = DefaultExceptionMessageDto
                    .builder()
                    .message(defaultExceptionMessage.defaultMessage())
                    .build();
            return Optional.of(defaultExceptionMessageDto);
        }
        return Optional.empty();
    }
}
