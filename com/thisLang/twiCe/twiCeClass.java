package com.thisLang.twiCe;

import java.util.List;
import java.util.Map;

class twiCeClass implements twiCeCallable {
  final String name;
  final twiCeClass superclass;
  private final Map<String, twiCeFunction> methods;

  twiCeClass(String name, twiCeClass superclass,
             Map<String, twiCeFunction> methods) {
    this.superclass = superclass;    
    this.name = name;
    this.methods = methods;
  }

  twiCeFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }

    if (superclass != null) {
        return superclass.findMethod(name);
      }

    return null;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    twiCeInstance instance = new twiCeInstance(this);
    twiCeFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }
    return instance;
  }

  @Override
  public int arity() {
    twiCeFunction initializer = findMethod("init");
    if (initializer == null) return 0;
    return initializer.arity();
  }
}