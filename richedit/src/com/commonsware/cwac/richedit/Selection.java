/***
  Copyright (c) 2008-2011 CommonsWare, LLC
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package com.commonsware.cwac.richedit;

import android.widget.EditText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Selection {
  public int start;
  public int end;

  public Selection(int _start, int _end) {
    start=_start;
    end=_end;

    if (start > end) {
      int temp=end;
      end=start;
      start=temp;
    }
  }

  Selection(EditText editor) {
    this(editor.getSelectionStart(), editor.getSelectionEnd());
  }

  public final boolean isEmpty() {
    return(start == end);
  }

  void apply(EditText editor) {
    editor.setSelection(start, end);
  }

  public final Selection getIntersection(final Selection other) {
    if (end < other.start || start > other.end) {
      return null;
    }

    final List<Integer> points = Arrays.asList(start, other.start, end, other.end);
    Collections.sort(points);

    return new Selection(points.get(1), points.get(2));
  }

  public final boolean equalsTo(final Selection other) {
    return this.start == other.start && this.end == other.end;
  }

  public final boolean isSet() {
    return start >= 0 && end >= 0;
  }

  public final void setStart(final int start) {
    this.start = (start < 0) ? 0 : start;
  }

  public final void setEnd(final int end) {
    this.end = (end < 0) ? 0 : end;
  }
}