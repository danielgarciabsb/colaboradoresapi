package br.com.colaboradoresapi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageComponent {

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @Autowired
    public MessageComponent(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, new Locale("pt", "BR"));
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

    public String get(String code, @Nullable Object[] args) {
        return accessor.getMessage(code, args);
    }

    public abstract static class Type {
        public static final String SALVO_SUCESSO = "mensagem.salvo.sucesso";
        public static final String SALVOS_SUCESSO = "mensagem.salvos.sucesso";
        public static final String OBTIDO_SUCESSO = "mensagem.obtido.sucesso";
        public static final String OBTIDOS_SUCESSO = "mensagem.obtidos.sucesso";
    }
}