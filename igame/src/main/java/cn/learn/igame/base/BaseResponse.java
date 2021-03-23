package cn.learn.igame.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 基础响应.
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class BaseResponse<T> implements Response<T> {

  /**
   * 响应码，当success为false时，code值代表服务端错误状态.
   */
  protected Integer code;

  /**
   * 响应的消息，当success为false时，message值为服务端的错误信息内容.
   */
  protected String message;

  /**
   * 响应的数据内容，success为false，该字段不会有值.
   */
  protected T data;


  @Override
  public Object getData(String key) {
    return data;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ResponseBuilder<T> {

    /**
     * 默认返回code为200.
     */
    private Integer code = 200;
    /**
     * 默认返回响应为操作成功.
     */
    private String message = "操作成功";
    private T data;

    public ResponseBuilder(T data) {
      this.data = data;
    }

    /**
     * 操作成功.
     */
    public ResponseBuilder<T> success() {
      this.code = 200;
      this.message = "操作成功";
      return this;
    }

    /**
     * 操作失败.
     */
    public ResponseBuilder<T> fail() {
      this.code = 500;
      this.message = "操作失败";
      return this;
    }

    /**
     * 自定义message.
     */
    public ResponseBuilder<T> message(String message) {
      this.message = message;
      return this;
    }

    /**
     * 自定义code.
     */
    public ResponseBuilder<T> code(int code) {
      this.code = code;
      return this;
    }

    /**
     * 自定义message和code.
     */
    public ResponseBuilder<T> codeMessage(int code, String message) {
      this.code = code;
      this.message = message;
      return this;
    }

    /**
     * 构建参数.
     */
    public ResponseBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public BaseResponse<T> build() {
      return new BaseResponse<>(this);
    }

  }


  private BaseResponse(ResponseBuilder<T> builder) {
    code = builder.code;
    message = builder.message;
    data = builder.data;
  }

  @Override
  public void putData(String key, Object value) {

  }
}
