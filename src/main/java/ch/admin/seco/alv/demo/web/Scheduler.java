package ch.admin.seco.alv.demo.web;

import ch.admin.seco.alv.demo.service.UserService;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler extends TimerTask {

    private final Timer timer;
    private final int interval = 60;
    private final UserService userService;

    public Scheduler(Timer timer, UserService userService) {
        this.timer = timer;
        this.userService = userService;
    }

    @Override
    public void run() {
        userService.updateStatus();
        System.out.println("User updated at: " + new Date());
    }
}
