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
    private final SendBotMessageService sendBotMessageService;

    public StatSenderServiceImpl(UserService userService, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void sendStat() {
        var users = userService.findAllUsers();
        for (User user : users) {
            String stat = StatGenerator.generateStat(user);
            if (!stat.isEmpty())
                sendBotMessageService.sendMessage(String.valueOf(user.getId()), stat);
        }
    }
}