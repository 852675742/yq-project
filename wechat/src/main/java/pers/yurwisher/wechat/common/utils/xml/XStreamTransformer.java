package pers.yurwisher.wechat.common.utils.xml;

import com.thoughtworks.xstream.XStream;
import pers.yurwisher.wechat.mp.in.WxMpXmlMessage;
import pers.yurwisher.wechat.mp.message.WxImageMessage;
import pers.yurwisher.wechat.mp.message.WxMusicMessage;
import pers.yurwisher.wechat.mp.message.WxNewsMessage;
import pers.yurwisher.wechat.mp.message.WxTextMessage;
import pers.yurwisher.wechat.mp.message.WxVideoMessage;
import pers.yurwisher.wechat.mp.message.WxVoiceMessage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XStream 转化类
 */
public class XStreamTransformer {
  private static final Map<Class<?>, XStream> CLASS_2_XSTREAM_INSTANCE = new HashMap<>();

  static {
    registerClass(WxMpXmlMessage.class);
    registerClass(WxImageMessage.class);
    registerClass(WxTextMessage.class);
    registerClass(WxVideoMessage.class);
    registerClass(WxVoiceMessage.class);
    registerClass(WxMusicMessage.class);
    registerClass(WxNewsMessage.class);
  }

  /**
   * xml -> pojo.
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class<T> clazz, String xml) {
    T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
    return object;
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class<T> clazz, InputStream is) {
    T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
    return object;
  }

  /**
   * pojo -> xml.
   */
  public static <T> String toXml(Class<T> clazz, T object) {
    return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
  }

  /**
   * 注册扩展消息的解析器.
   *
   * @param clz     类型
   * @param xStream xml解析器
   */
  public static void register(Class<?> clz, XStream xStream) {
    CLASS_2_XSTREAM_INSTANCE.put(clz, xStream);
  }

  /**
   * 会自动注册该类及其子类.
   *
   * @param clz 要注册的类
   */
  private static void registerClass(Class<?> clz) {
    XStream xstream = XStreamInitializer.getInstance();

    xstream.processAnnotations(clz);
    xstream.processAnnotations(getInnerClasses(clz));
    if (clz.equals(WxMpXmlMessage.class)) {
      //模板消息推送成功的消息是MsgID，其他消息推送过来是MsgId
      xstream.aliasField("MsgID", WxMpXmlMessage.class, "msgId");
    }

    register(clz, xstream);
  }

  private static Class<?>[] getInnerClasses(Class<?> clz) {
    Class<?>[] innerClasses = clz.getClasses();
    if (innerClasses == null) {
      return null;
    }

    List<Class<?>> result = new ArrayList<>();
    result.addAll(Arrays.asList(innerClasses));
    for (Class<?> inner : innerClasses) {
      Class<?>[] innerClz = getInnerClasses(inner);
      if (innerClz == null) {
        continue;
      }

      result.addAll(Arrays.asList(innerClz));
    }

    return result.toArray(new Class<?>[0]);
  }
}
