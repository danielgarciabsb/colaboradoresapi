package br.com.colaboradoresapi.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Message {

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @Autowired
    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, new Locale("pt", "BR"));
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

    public abstract static class Type {
        public static final String SUCESSO = "mensagem.sucesso";
    }

}