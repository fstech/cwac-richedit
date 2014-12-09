package com.futuresimple.base.richedit.text.style;

public class UnorderedListSpan implements ListSpan {

  @Override
  public Type getListType() {
    return Type.UNORDERED;
  }

  @Override
  public final ListSpan newSpan() {
    return new UnorderedListSpan();
  }
}
