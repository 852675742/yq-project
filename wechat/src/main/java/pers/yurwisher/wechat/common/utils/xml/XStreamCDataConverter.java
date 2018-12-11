package pers.yurwisher.wechat.common.utils.xml;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * 将数据通过CDATA包装起来
 * @author yq
 */
public class XStreamCDataConverter extends StringConverter {

  @Override
  public String toString(Object obj) {
    return "<![CDATA[" + super.toString(obj) + "]]>";
  }

}
