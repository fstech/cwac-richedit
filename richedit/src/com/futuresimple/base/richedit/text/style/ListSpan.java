package com.futuresimple.base.richedit.text.style;

public interface ListSpan {

  public static final int SPAN_NUMBER = 100;
  public static final int SPAN_BULLET = 101;

  Type getListType();

  public enum Type {
    ORDERED, UNORDERED
  }
}