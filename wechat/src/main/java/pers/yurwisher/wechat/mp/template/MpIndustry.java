package pers.yurwisher.wechat.mp.template;

import lombok.Data;

import java.io.Serializable;

/**
 * 公众号的行业信息
 * @author yq
 */
@Data
public class MpIndustry implements Serializable {

  private static final long serialVersionUID = 570036007489318404L;
  private Industry primaryIndustry;

  private Industry secondIndustry;

  /**
   * @author 行业信息
   */
  @Data
  public static class Industry implements Serializable{
    private static final long serialVersionUID = 891138155978558874L;
    /**主行业*/
    private String firstClass;
    /**副行业*/
    private String secondClass;
    /**行业代码*/
    private String code ;

    public Industry(String firstClass, String secondClass, String code) {
      this.firstClass = firstClass;
      this.secondClass = secondClass;
      this.code = code;
    }

    public Industry() {
    }
  }
}
