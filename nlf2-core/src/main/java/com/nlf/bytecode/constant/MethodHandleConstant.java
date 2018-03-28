package com.nlf.bytecode.constant;

/**
 * 常量-MethodHandle
 * 
 * @author 6tail
 *
 */
public class MethodHandleConstant extends AbstractConstant{
  private int referenceKind;
  private int referenceIndex;

  public int getReferenceKind() {
    return referenceKind;
  }

  public void setReferenceKind(int referenceKind) {
    this.referenceKind = referenceKind;
  }

  public int getReferenceIndex() {
    return referenceIndex;
  }

  public void setReferenceIndex(int referenceIndex) {
    this.referenceIndex = referenceIndex;
  }

  public MethodHandleConstant toMethodHandleConstant(){
    return this;
  }
}