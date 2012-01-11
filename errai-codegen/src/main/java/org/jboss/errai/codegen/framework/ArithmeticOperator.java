/*
 * Copyright 2011 JBoss, by Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.codegen.framework;

import org.jboss.errai.codegen.framework.meta.MetaClass;

/**
 * @author Christian Sadilek <csadilek@redhat.com>
 */
public enum ArithmeticOperator implements Operator {
  Addition("+", 0, Number.class),
  Subtraction("-", 0, Number.class),
  Multiplication("*", 1, Number.class),
  Division("/", 1, Number.class),
  Remainder("%", 1, Number.class);
  
  private final Operator operator;

  ArithmeticOperator(String canonicalString, int operatorPrecedence, Class<?>... constraints) {
    operator = new OperatorImpl(canonicalString, operatorPrecedence, constraints);
  }

  @Override
  public String getCanonicalString() {
    return operator.getCanonicalString();
  }

  @Override
  public int getOperatorPrecedence() {
    return operator.getOperatorPrecedence();
  }

  @Override
  public boolean isHigherPrecedenceThan(Operator operator) {
    return operator.getOperatorPrecedence() < getOperatorPrecedence();
  }

  @Override
  public boolean isEqualOrHigherPrecedenceThan(Operator operator) {
    return operator.getOperatorPrecedence() <= getOperatorPrecedence();
  }

  @Override
  public boolean canBeApplied(MetaClass clazz) {
    return operator.canBeApplied(clazz);
  }

  @Override
  public void assertCanBeApplied(MetaClass clazz) {
    operator.assertCanBeApplied(clazz);
  }
}