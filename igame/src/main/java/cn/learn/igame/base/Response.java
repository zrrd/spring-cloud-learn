package cn.learn.igame.base;

/**
 * json响应消息对象.
 *
 * @author caoquanlong
 */
public interface Response<T> {

  Integer getCode();

  String getMessage();

  T getData();

  Object getData(String key);

  void putData(String key, Object value);
}
