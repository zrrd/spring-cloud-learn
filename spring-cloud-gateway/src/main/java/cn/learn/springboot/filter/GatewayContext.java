package cn.learn.springboot.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author shaoyijiong
 * @date 2019/5/31
 */
@Getter
@Setter
@ToString
public class GatewayContext {

  public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";

  /**
   * JSON 请求
   */
  private String cacheBody;
  /**
   * 表单请求
   */
  private MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
  /**
   * 路径参数
   */
  private MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
  /**
   * 请求路径
   */
  private String path;
}
