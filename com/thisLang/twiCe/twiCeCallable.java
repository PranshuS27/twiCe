package com.thisLang.twiCe;

import java.util.List;

interface twiCeCallable {
    int arity();
  Object call(Interpreter interpreter, List<Object> arguments);
}