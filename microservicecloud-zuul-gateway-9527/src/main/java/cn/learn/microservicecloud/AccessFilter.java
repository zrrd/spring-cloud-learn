package cn.learn.microservicecloud;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 自定义过滤器,实现登录校验.
 *
 * @author 邵益炯
 * @date 2018/10/31
 */
@Component
@Slf4j
public class AccessFilter extends ZuulFilter {

  /**
   * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行.
   */
  @Override
  public String filterType() {
    // FilterConstants.ROUTE_TYPE; 路由请求时
    // FilterConstants.ERROR_TYPE; 发生错误时
    // FilterConstants.POST_TYPE;  在route与error后调用
    return FilterConstants.PRE_TYPE;
  }

  /**
   * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行. 数字越小优先级越高
   */
  @Override
  public int filterOrder() {
    return 0;
  }


  /**
   * 判断该过滤器是否需要被执行。这里我们直接返回了true， 因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围.
   */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  /**
   * <p>过滤器的具体逻辑。这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求， 不对其进行路由，</p>
   * <p>然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，</p>
   * <p>当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。</p>
   */
  @Override
  public Object run() {
    //请求上下文  主要包含 request response header
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();

    Object accessToken = request.getParameter("accessToken");
    if (accessToken == null) {
      log.warn("access token is empty");
      //拦截
      ctx.setSendZuulResponse(false);
      //设置响应状态码
      ctx.setResponseStatusCode(401);
    }
    log.info("access token ok");
    return null;
  }
}
