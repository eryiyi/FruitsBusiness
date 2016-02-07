package com.lbins.FruitsBusiness.bean;

import java.io.Serializable;

public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Response(int code){
		Response.code = code;
	}
	public static int code;
	
	public Response(){
		super();
	}
}
