/*
 * Copyright (c) 2013, Andreas Billmann <abi@geofroggerfx.de>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.diguits.javafx.controls;

import java.util.List;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Multi-Column-Row list cell to shows the most important data in a list.
 *
 * @author Andreas
 */
public abstract class RichTextListCell<T> extends ListCell<T> {
  private final HBox container = new HBox();
  protected final TextFlow textFlow = new TextFlow();
  private final ImageView icon = new ImageView();

  public RichTextListCell() {
    configureContainer();
    configureIcon();
    configureText();
    addControlsToContainer();
  }

  @Override
  public void updateItem(T value, boolean empty) {
    super.updateItem(value, empty);
    if (empty) {
      clearContent();
    } else {
      addContent(value);
    }
  }

  private void configureContainer() {

  }

  private void configureIcon() {
    HBox.setHgrow(icon, Priority.SOMETIMES);
  }

  private void configureText() {
	  HBox.setHgrow(textFlow, Priority.ALWAYS);
  }

  private void addControlsToContainer() {
	  container.getChildren().add(icon);
	  container.getChildren().add(textFlow);

  }

  private void clearContent() {
    setText(null);
    setGraphic(null);
  }

  private void addContent(T value) {
    setText(null);
    icon.setImage(getIcon(value));
    textFlow.getChildren().clear();
    textFlow.getChildren().addAll(getTexts(value));

    setStyleClassDependingOnFoundState(value);
    setGraphic(container);
  }

  protected abstract List<Text> getTexts(T value);// {
//	List<Text> result = new ArrayList<Text>();
//	result.add(new Text("QQQ"));
//	result.add(new Text("WWW"));
//	return result;
//}

protected abstract Image getIcon(T value);

  protected String getText(T value){
	  if(value!=null)
	  return value.toString();
	  return null;
  }


private void setStyleClassDependingOnFoundState(T value) {

  }
}