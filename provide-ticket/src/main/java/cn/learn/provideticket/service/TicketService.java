package cn.learn.provideticket.service;

import org.springframework.stereotype.Service;

/**
 * 提供一张电影票.
 *
 * @author shaoyijiong
 * @date 2018/8/4
 */
@Service
public class TicketService {

  public String getTicket() {
    System.out.println("访问了8001");
    return "<盗梦空间>";
  }
}
