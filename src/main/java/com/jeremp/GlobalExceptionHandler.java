
package com.jeremp;



import com.jeremp.dto.GbException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jpass_000
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public static final String DEFAULT_ERROR_VIEW = "error";



    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GbException defaultErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {

        LOG.error("oups", e);

        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new GbException(e.getClass().toString(), e.getMessage());
        
        // Otherwise setup and send the user to a default error-view.
        /*
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
        */
    }
    
}
