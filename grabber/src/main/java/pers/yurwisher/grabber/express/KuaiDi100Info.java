package pers.yurwisher.grabber.express;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 快递100接口返回
 */
@Data
public class KuaiDi100Info implements Serializable {

	private static final long serialVersionUID = 5774129853644394700L;

	private String message;
	private String nu;
	private String ischeck;
	private String condition;
	private String com;
	private String status;
	private String state;
	private List<KuaiDi100InfoMember> data;

	/**
	 * 快递100明细
	 */
	@Data
	public static class KuaiDi100InfoMember implements Serializable{

		private static final long serialVersionUID = -543758830144118837L;

		private String time;
		private String ftime;
		private String context;
		private String location;
	}
}
