package cn.learn.provideticket.controller;

import cn.learn.provideticket.service.TicketService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 * @author shaoyijiong
 * @date 2018/8/4
 */
@RestController
public class TicketController {

  @Resource
  private TicketService ticketService;

  @GetMapping("/ticket")
  public String getTicket() {
    return ticketService.getTicket();
  }
}
