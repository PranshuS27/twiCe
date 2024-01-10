package com.thisLang.twiCe;

import java.util.HashMap;
import java.util.Map;

class twiCeInstance {
  private twiCeClass klass;
  private final Map<String, Object> fields = new HashMap<>();


  twiCeInstance(twiCeClass klass) {
    this.klass = klass;
  }

  Object get(Token name) {
    if (fields.containsKey(name.lexeme)) {
      return fields.get(name.lexeme);
    }
    twiCeFunction method = klass.findMethod(name.lexeme);
    if (method != null) return method;
    if (method != null) return method.bind(this);

    throw new RuntimeError(name, 
        "Undefined property '" + name.lexeme + "'.");
  }

  void set(Token name, Object value) {
    fields.put(name.lexeme, value);
  }

  @Override
  public String toString() {
    return klass.name + " instance";
  }
}