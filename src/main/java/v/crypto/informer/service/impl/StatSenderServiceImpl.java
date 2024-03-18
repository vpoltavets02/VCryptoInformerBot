package v.crypto.informer.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import v.crypto.informer.model.User;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.StatSenderService;
import v.crypto.informer.util.StatGenerator;

@Service
public class StatSenderServiceImpl implements StatSenderService {
    private final UserService userService;
    private final StatGenerator statGenerator;
    private final SendBotMessageService sendBotMessageService;

    public StatSenderServiceImpl(UserService userService, StatGenerator statGenerator, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.statGenerator = statGenerator;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void sendStat() {
        var users = userService.findByNotificationsTrue();
        for (User user : users) {
            String stat = statGenerator.generateStat(user);
            if (!stat.isEmpty())
                sendBotMessageService.sendMessage(String.valueOf(user.getId()), stat);
        }
    }
}