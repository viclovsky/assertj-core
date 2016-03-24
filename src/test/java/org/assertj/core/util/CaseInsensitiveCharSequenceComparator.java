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
package org.assertj.core.util;

import java.util.Comparator;

/**
 * @author Mikhail Mazursky
 */
public class CaseInsensitiveCharSequenceComparator implements Comparator<CharSequence> {

  public final static CaseInsensitiveCharSequenceComparator instance = new CaseInsensitiveCharSequenceComparator();

  @Override
  public int compare(CharSequence s1, CharSequence s2) {

    return CaseInsensitiveStringComparator.instance.compare(s1.toString(), s2.toString());
  }
}
