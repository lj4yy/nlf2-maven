package com.nlf.core;

import java.io.IOException;

/**
 * 响应接口
 * 
 * @author 6tail
 *
 */
public interface IResponse{
  /**
   * 发送对象
   * 
   * @param o 对象
   * @throws IOException
   */
  void send(Object o) throws IOException;

  /**
   * 发送字符串
   * 
   * @param s 字符串
   * @throws IOException
   */
  void sendString(String s) throws IOException;
  
  /**
   * 发送字符串
   * 
   * @param s 字符串
   * @param contentType
   * @throws IOException
   */
  void sendString(String s,String contentType) throws IOException;

  /**
   * 发送流
   * 
   * @param inputStream 输入流
   * @throws IOException
   */
  void sendStream(java.io.InputStream inputStream) throws IOException;
}