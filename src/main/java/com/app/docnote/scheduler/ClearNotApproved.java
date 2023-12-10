package com.app.docnote.scheduler;

import com.app.docnote.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearNotApproved {

    private final UserService userService;

    public ClearNotApproved(UserService userService) {
        this.userService = userService;
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void clearNotApproved(){
        userService.clearNotApproved();
    }
}
