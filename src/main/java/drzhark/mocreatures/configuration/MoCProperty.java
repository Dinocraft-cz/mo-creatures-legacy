package drzhark.mocreatures.configuration;

import java.util.List;

public class MoCProperty {
  private String name;
  
  public String value;
  
  public String comment;
  
  public List<String> valueList;
  
  private final boolean wasRead;
  
  private final boolean isList;
  
  private final Type type;
  
  public enum Type {
    STRING, INTEGER, BOOLEAN, DOUBLE;
    
    public static Type tryParse(char id) {
      for (Type type : Type.values()) {
        if (type.getID() == id)
          return type; 
      } 
      return STRING;
    }
    
    public char getID() {
      return name().charAt(0);
    }
  }
  
  private boolean changed = false;
  

  
  public MoCProperty(String name, String value, Type type) {
    this(name, value, type, false);
  }
  

  
  MoCProperty(String name, String value, Type type, boolean read) {
    setName(name);
    this.value = value;
    this.type = type;
    wasRead = read;
    isList = false;
  }
  
  public MoCProperty(String name, List<String> values, Type type) {
    this(name, values, type, false);
  }
  

  
  MoCProperty(String name, List<String> values, Type type, boolean read) {
    setName(name);
    this.type = type;
    valueList = values;
    wasRead = read;
    isList = true;
  }
  
  public String getString() {
    return value;
  }
  
  public int getInt() {
    return getInt(-1);
  }
  
  public int getInt(int _default) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return _default;
    } 
  }
  
  public boolean isIntValue() {
    try {
      Integer.parseInt(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    } 
  }
  
  public boolean getBoolean(boolean _default) {
    if (isBooleanValue())
      return Boolean.parseBoolean(value); 
    return _default;
  }
  
  public boolean isBooleanValue() {
    return ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value));
  }
  
  public boolean isDoubleValue() {
    try {
      Double.parseDouble(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    } 
  }
  

  
  public boolean isIntList() {
    for (String value : valueList) {
      try {
        Integer.parseInt(value);
      } catch (NumberFormatException e) {
        return false;
      } 
    } 
    return true;
  }
  

  
  public boolean isBooleanList() {
    for (String value : valueList) {
      if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value))
        return false; 
    } 
    return true;
  }
  

  
  public boolean isDoubleList() {
    for (String value : valueList) {
      try {
        Double.parseDouble(value);
      } catch (NumberFormatException e) {
        return false;
      } 
    } 
    return true;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  

  
  public Type getType() {
    return type;
  }
  
  public boolean isList() {
    return isList;
  }
  
  public boolean hasChanged() {
    return changed;
  }
  
  void resetChangedState() {
    changed = false;
  }
  
  public void set(String value) {
    this.value = value;
    changed = true;
  }
  
  public void set(List<String> values) {
    valueList = values;
    changed = true;
  }
  
  public void set(int value) {
    set(Integer.toString(value));
  }
  
  public void set(boolean value) {
    set(Boolean.toString(value));
  }
  
  public void set(double value) {
    set(Double.toString(value));
  }
}
