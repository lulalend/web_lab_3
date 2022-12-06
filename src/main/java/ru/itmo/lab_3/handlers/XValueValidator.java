package ru.itmo.lab_3.handlers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.math.BigDecimal;

@ManagedBean
@ApplicationScoped
@FacesValidator("xValueValidator")
public class XValueValidator implements Validator {
    private static final String WRONG_INTERVAL_MESSAGE =
            "X должен лежать в интервале (-3;3)!";
    private static final BigDecimal LEFT_BOUND = new BigDecimal("-3");
    private static final BigDecimal RIGHT_BOUND = new BigDecimal("3");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        BigDecimal xValue = (BigDecimal) value;

        if(xValue.compareTo(LEFT_BOUND) <= 0 || xValue.compareTo(RIGHT_BOUND) >= 0)
            processError(WRONG_INTERVAL_MESSAGE);
    }

    private void processError(String errorMessage) {
        FacesMessage message = new FacesMessage(errorMessage);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }
}
