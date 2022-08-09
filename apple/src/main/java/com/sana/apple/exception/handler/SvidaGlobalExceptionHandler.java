package com.sana.apple.exception.handler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sana.apple.dto.ApiResponse;
import com.sana.apple.exception.AppException;
import com.sana.apple.exception.BadCredentialsException;
import com.sana.apple.exception.BadRequestException;
import com.sana.apple.exception.EmptyResourceListException;
import com.sana.apple.exception.ResourceAlreadyInUseException;
import com.sana.apple.exception.ResourceNotFoundException;
import com.sana.apple.exception.ResourceRegistrationException;
import com.sana.apple.exception.UserLoginException;

@RestControllerAdvice
public class SvidaGlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(SvidaGlobalExceptionHandler.class);
	private final MessageSource messageSource;

	@Autowired
	public SvidaGlobalExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<ObjectError> allErrors = result.getAllErrors();
		ApiResponse response = new ApiResponse();
		response.setStatus(false);
		response.setMessage(processAllErrors(allErrors).stream().collect(Collectors.joining("\n")));
		return response;
	}

	/**
	 * Utility Method to generate localized message for a list of field errors
	 *
	 * @param allErrors the field errors
	 * @return the list
	 */
	private List<String> processAllErrors(List<ObjectError> allErrors) {
		return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
	}

	/**
	 * Resolve localized error message. Utiity method to generate a localized error
	 * message
	 *
	 * @param objectError the field error
	 * @return the string
	 */
	private String resolveLocalizedErrorMessage(ObjectError objectError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
		logger.info(localizedErrorMessage);
		return localizedErrorMessage;
	}

	@ExceptionHandler(value = AppException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiResponse handleAppException(AppException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = ResourceAlreadyInUseException.class)
	@ResponseStatus(HttpStatus.IM_USED)
	@ResponseBody
	public ApiResponse handleResourceAlreadyInUseException(ResourceAlreadyInUseException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse handleBadRequestException(BadRequestException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = BadCredentialsException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public ApiResponse handleBadCredentialsException(BadCredentialsException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = EmptyResourceListException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public ApiResponse handleEmptyResourceListException(EmptyResourceListException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = ResourceRegistrationException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public ApiResponse handleUserRegistrationException(ResourceRegistrationException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = UserLoginException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public ApiResponse handleUserUserLoginException(UserLoginException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(false);
		apiResponse.setMessage(ex.getMessage());
		return apiResponse;
	}

}
