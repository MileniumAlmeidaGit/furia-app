package com.seuprojeto.service;

import com.seuprojeto.model.Notification;
import com.seuprojeto.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j   // Essa anotação gera a variável 'log'
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // Injeção via construtor
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNofications() {
        log.info("Chamando getAllNofications() para recuperar notificações");
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        log.info("Buscando notificação com id: {}", id);
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification createNotification(Notification notification) {
        log.info("Criando nova notificação: {}", notification);
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long id, Notification notificationDetails) {
        log.info("Atualizando notificação com id: {}", id);
        return notificationRepository.findById(id).map(notification -> {
            notification.setContent(notificationDetails.getContent());
            notification.setIsRead(notificationDetails.getIsRead());
            return notificationRepository.save(notification);
        }).orElse(null);
    }

    public void deleteNotification(Long id) {
        log.info("Deletando notificação com id: {}", id);
        notificationRepository.deleteById(id);
    }
}
