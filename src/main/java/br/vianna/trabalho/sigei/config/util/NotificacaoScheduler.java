package br.vianna.trabalho.sigei.config.util;

import br.vianna.trabalho.sigei.service.NotificacaoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class NotificacaoScheduler {

    private final NotificacaoService notificacaoService;

    public NotificacaoScheduler(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void enviarNotificacoesDiarias() {
        notificacaoService.verificarEventosHoje();
    }
}
