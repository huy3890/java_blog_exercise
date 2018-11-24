package com.exercise.util;

public enum StringConstant {

  LASTMODIFIED("lastModified"), STATUS("status"), PUBLISHDATE("publishDate");
  private String value;

  StringConstant(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
