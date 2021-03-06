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

import com.futuresimple.base.richedit.text.EffectsHandler;

import android.text.Spannable;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.List;

public class StyleEffect extends Effect<Boolean, StyleSpan> {
  private final EffectsHandler mEffectsHandler;
  private int style;

  StyleEffect(int style) {
    this.style=style;
    mEffectsHandler = new EffectsHandler(this);
  }

  @Override
  boolean existsInSelection(RichEditText editor) {
    return mEffectsHandler.presentInsideSelection(editor.getText(), new Selection(editor));
  }

  public final int getStyle() {
    return style;
  }

  @Override
  Boolean valueInSelection(RichEditText editor) {
    return(existsInSelection(editor));
  }

  @Override
  void applyToSelection(RichEditText editor, Boolean add) {
    mEffectsHandler.applyToSelection(editor.getText(), new Selection(editor));
  }

  @Override
  public final StyleSpan newEffect() {
    return new StyleSpan(style);
  }

  @Override
  public final List<StyleSpan> getAllEffectsFrom(final Spannable text, final Selection selection) {
    final StyleSpan[] styleSpans = text.getSpans(selection.start, selection.end, StyleSpan.class);
    final List<StyleSpan> result = new ArrayList<>();
    for (final StyleSpan span : styleSpans) {
      if (span.getStyle() == this.style) {
        result.add(span);
      }
    }
    return result;
  }

}
