package common.rest;

import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import common.codes.ERROR_CODES;
import common.exception.ValidationException;
import common.repository.RepositoryCommon;

/**
 * @author Manuel
 *
 */
public class RestCommon {

    /**
     * Method to simplify call of method.
     * 
     * @param resourceNaming
     * @param logger
     */
    protected void logInvokeOfMethod(RESOURCE_NAMING resourceNaming, Logger logger) {
        logger.info(ResourceUtils.getLogMessage(resourceNaming));
    }

    /**
     * Method checks if passed variable is not empty.
     * 
     * @param variable
     * @param errorCode
     * @throws ValidationException
     */
    protected void validateIsNotEmpty(String variable, ERROR_CODES errorCode) throws ValidationException {
        if (StringUtils.isEmpty(variable)) {
            throw new ValidationException(errorCode);
        }
    }

    /**
     * Method checks if item with passed name exists in repository.
     * 
     * @param name
     * @param extendedRepository
     * @param errorCode
     * @return
     * @throws ValidationException
     */
    protected <T> T validateExists(String name, RepositoryCommon<T> extendedRepository, ERROR_CODES errorCode) throws ValidationException {

        T result = extendedRepository.findByName(name);

        if (null == result) {
            throw new ValidationException(errorCode);
        }

        return result;
    }

    /**
     * Method checks if item with passed name doesn't exist in repository.
     * 
     * @param name
     * @param extendedRepository
     * @param errorCode
     * @return
     * @throws ValidationException
     */
    protected <T> void validateNotExists(String name, RepositoryCommon<T> extendedRepository, ERROR_CODES errorCode) throws ValidationException {

        T result = extendedRepository.findByName(name);

        if (null != result) {
            throw new ValidationException(errorCode);
        }

    }

}
