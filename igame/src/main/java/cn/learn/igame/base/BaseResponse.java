package cn.learn.igame.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础响应.
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
@Getter
@Setter
@EqualsAndHashCode
public class BaseResponse<T> implements Response {

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

  public static class Builder<T> {

    private Integer code;
    private String message;
    private T data;

    /**
     * 操作成功.
     */
    public Builder<T> success() {
      this.code = 200;
      this.message = "操作成功";
      return this;
    }

    /**
     * 操作失败.
     */
    public Builder<T> fail() {
      this.code = 500;
      this.message = "操作失败";
      return this;
    }

    /**
     * 构建参数.
     */
    public Builder<T> data(T data) {
      this.data = data;
      return this;
    }

    public BaseResponse build() {
      return new BaseResponse<>(this);
    }

  }


  private BaseResponse(Builder<T> builder) {
    code = builder.code;
    message = builder.message;
    data = builder.data;
  }

  @Override
  public void putData(String key, Object value) {

  }
}
