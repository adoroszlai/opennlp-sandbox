/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.opennlp.caseditor.namefinder;

import org.apache.opennlp.caseditor.OpenNLPPlugin;
import org.apache.opennlp.caseditor.OpenNLPPreferenceConstants;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class NameFinderPreferencePage extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage {

  private StringFieldEditor modelPath;
  
  private StringFieldEditor sentenceType;
  private StringFieldEditor tokenType;
  private StringFieldEditor nameType;
  
  
  public NameFinderPreferencePage() {
    setPreferenceStore(OpenNLPPlugin.getDefault().getPreferenceStore());
    setDescription("UIMA Annotation Editor Preferences.");
  }

  @Override
  public void init(IWorkbench workbench) {
  }

  @Override
  protected void createFieldEditors() {
    // editor line length hint
    modelPath = new StringFieldEditor(
            OpenNLPPreferenceConstants.MODEL_PATH,
            "Model Path", getFieldEditorParent());
    addField(modelPath);
    
    sentenceType = new StringFieldEditor(
        OpenNLPPreferenceConstants.SENTENCE_TYPE,
        "Sentence Type", getFieldEditorParent());
    addField(sentenceType);
    
    tokenType = new StringFieldEditor(
        OpenNLPPreferenceConstants.TOKEN_TYPE,
        "Token Type", getFieldEditorParent());
    addField(tokenType);
    
    nameType = new StringFieldEditor(
        OpenNLPPreferenceConstants.NAME_TYPE,
        "Name Type", getFieldEditorParent());
    addField(nameType);
  }
  
}
