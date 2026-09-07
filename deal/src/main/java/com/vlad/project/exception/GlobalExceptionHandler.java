package com.vlad.project.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    public static final String DB_SAVE_EXCEPTION = "Ошибка при сохранении в базу данных";
    public static final String UNKNOWN_MESSAGE = "unknown";
    private final ProblemDetailFactory problemDetailFacrory;

    @ExceptionHandler(CreateApplicationException.class)
    public ResponseEntity<ProblemDetail> showExceptionAboutSql(CreateApplicationException ex,
                                                               HttpServletRequest request) {
        log.error("Произошло исключение -> {}; вызвался метод -> showExceptionAboutSql", ex.getClass());

        var messageError = ex.getMessage() != null ? ex.getMessage() : UNKNOWN_MESSAGE;
        ProblemDetail problemDetail = problemDetailFacrory.detailFactory(
                HttpStatus.BAD_REQUEST,
                messageError,
                request);

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ProblemDetail> showValidExceptionFromDifferentAPI(HttpClientErrorException ex,
                                                                            HttpServletRequest request) {
        log.error("Произошло исключение -> {}; вызвался метод -> showValidExceptionFromDifferentAPI", ex.getClass());
        ObjectMapper mapper = new ObjectMapper();

        var messageErrorResponse =ex.getResponseBodyAsString();
        JsonNode json = mapper.readTree(messageErrorResponse);

        String detail = json.get("detail").asString();
        String messageError = detail != null ? detail : UNKNOWN_MESSAGE;
        ProblemDetail problemDetail = problemDetailFacrory.detailFactory(
                HttpStatus.BAD_REQUEST,
                messageError,
                request);

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ProblemDetail> illegalAccessError(RuntimeException ex,
                                                            HttpServletRequest request) {
        log.error("Произошло исключение -> {}; вызвался метод -> illegalAccessError", ex.getClass());

        var messageError = ex.getMessage() != null ? ex.getMessage() : UNKNOWN_MESSAGE;
        ProblemDetail problemDetail = problemDetailFacrory.detailFactory(
                HttpStatus.BAD_REQUEST,
                messageError,
                request);

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> dataBaseSaveException(DataIntegrityViolationException ex,
                                                            HttpServletRequest request) {
        log.error("Произошло исключение -> {}; вызвался метод -> dataBaseSaveException", ex.getClass());

        ProblemDetail problemDetail = problemDetailFacrory.detailFactory(
                HttpStatus.BAD_REQUEST,
                DB_SAVE_EXCEPTION,
                request);

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ProblemDetail> dataBaseUpdateException(InvalidDataAccessApiUsageException ex,
                                                            HttpServletRequest request) {
        log.error("Произошло исключение -> {}; вызвался метод -> dataBaseSaveException", ex.getClass());

        ProblemDetail problemDetail = problemDetailFacrory.detailFactory(
                HttpStatus.BAD_REQUEST,
                DB_SAVE_EXCEPTION,
                request);

        return ResponseEntity.badRequest().body(problemDetail);
    }

}
