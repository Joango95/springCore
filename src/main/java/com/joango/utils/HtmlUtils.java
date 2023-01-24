package com.joango.utils;

import com.joango.model.DTO.TicketDTO;
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
}
