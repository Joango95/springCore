package com.joango.utils;

import com.joango.model.DTO.TicketDTO;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.List;

public class HtmlUtils {
    static public String generateTicketHtml(List<TicketDTO> tickets){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        context.setVariable("tickets", tickets);

        return templateEngine.process("/templates/tickets", context);
    }

    static public ModelAndView createTemplateCustomSingleValue(
        String variableName,
        Object value,
        String templateName
    ){
        ModelAndView mav = new ModelAndView();
        mav.addObject(variableName, value);
        mav.setViewName(templateName);
        return mav;
    }

    static public ModelAndView createErrorHandlingTemplate(Throwable error, String templateName) {
        return createTemplateCustomSingleValue(
            "message",
            error.getMessage(),
            templateName
        );
    }
}
