/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.api.objectarray;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.ObjectArrayAssert;
import org.assertj.core.api.ObjectArrayAssertBaseTest;
import org.assertj.core.internal.ComparatorBasedComparisonStrategy;
import org.assertj.core.internal.ObjectArrayElementComparisonStrategy;
import org.assertj.core.internal.ObjectArrays;
import org.junit.Before;
import org.junit.Test;

public class ObjectArrayAssert_usingRecursiveFieldByFieldElementComparator_Test extends ObjectArrayAssertBaseTest {

  private ObjectArrays arraysBefore;

  @Before
  public void before() {
    arraysBefore = getArrays(assertions);
  }

  @Override
  protected ObjectArrayAssert<Object> invoke_api_method() {
    return assertions.usingRecursiveFieldByFieldElementComparator();
  }

  @Override
  protected void verify_internal_effects() {
    assertThat(arraysBefore).isNotSameAs(getArrays(assertions));
    assertThat(getArrays(assertions).getComparisonStrategy()).isInstanceOf(ComparatorBasedComparisonStrategy.class);
    assertThat(getObjects(assertions).getComparisonStrategy()).isInstanceOf(ObjectArrayElementComparisonStrategy.class);
  }

  @Test
  public void successful_isEqualTo_assertion_using_recursive_field_by_field_element_comparator() {
    Foo [] array1 = { new Foo("id", new Bar(1)) };
    Foo [] array2 = { new Foo("id", new Bar(1)) };
    assertThat(array1).usingRecursiveFieldByFieldElementComparator().isEqualTo(array2);
  }

  @Test
  public void successful_isIn_assertion_using_recursive_field_by_field_element_comparator() {
    Foo [] array1 = { new Foo("id", new Bar(1)) };
    Foo [] array2 = { new Foo("id", new Bar(1)) };
    assertThat(array1).usingRecursiveFieldByFieldElementComparator().isIn(new Object[] {(array2)});
  }

  @Test
  public void failed_isEqualTo_assertion_using_recursive_field_by_field_element_comparator() {
    Foo [] array1 = { new Foo("id", new Bar(1)) };
    Foo [] array2 = { new Foo("id", new Bar(2)) };
    try {
      assertThat(array1).usingRecursiveFieldByFieldElementComparator().isEqualTo(array2);
    } catch (AssertionError e) {
      // @format:off
      assertThat(e).hasMessage(format("%nExpecting:%n" +
                                      " <[Foo(id=id, bar=Bar [id=1])]>%n" +
                                      "to be equal to:%n" +
                                      " <[Foo(id=id, bar=Bar [id=2])]>%n" +
                                      "when comparing elements using 'recursive field/property by field/property comparator on all fields/properties' but was not."));
      // @format:on
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void failed_isIn_assertion_using_recursive_field_by_field_element_comparator() {
    Foo[] array1 = { new Foo("id", new Bar(1)) };
    Foo[] arry2 = { new Foo("id", new Bar(2)) };
    try {
      assertThat(array1).usingRecursiveFieldByFieldElementComparator().isIn(new Object[] { arry2 });
    } catch (AssertionError e) {
      assertThat(e).hasMessage(String.format("%nExpecting:%n" +
                                             " <[Foo(id=id, bar=Bar [id=1])]>%n" +
                                             "to be in:%n" +
                                             " <[[Foo(id=id, bar=Bar [id=2])]]>%n" +
                                             "when comparing elements using 'recursive field/property by field/property comparator on all fields/properties'"));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  public static class Foo {
    public String id;
    public Bar bar;

    public Foo(String id, Bar bar) {
      this.id = id;
      this.bar = bar;
    }

    @Override
    public String toString() {
      return "Foo(id=" + id + ", bar=" + bar + ")";
    }
  }

  public static class Bar {
    public int id;

    public Bar(int id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return "Bar [id=" + id + "]";
    }
  }
}