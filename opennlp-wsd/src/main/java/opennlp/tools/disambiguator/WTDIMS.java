/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package opennlp.tools.disambiguator;

import net.sf.extjwnl.data.POS;

public class WTDIMS {

  // Attributes related to the context
  protected String[] sentence;
  protected String[] posTags;
  protected String[] lemmas;
  protected int wordIndex;
  protected int sense;
  protected String[] senseIDs;

  // Attributes related to IMS features
  protected String[] posOfSurroundingWords;
  protected String[] surroundingWords;
  protected String[] localCollocations;
  protected String[] features;

  public WTDIMS(String[] sentence, String[] posTags, String[] lemmas,
                int wordIndex) {
    setSentence(sentence);
    setPosTags(posTags);
    setLemmas(lemmas);
    setWordIndex(wordIndex);
  }

  public WTDIMS(String[] sentence, String[] posTags, String[] lemmas,
                int wordIndex, String[] senseIDs) {
    this(sentence, posTags, lemmas, wordIndex);
    setSenseIDs(senseIDs);
  }

  public WTDIMS(String[] sentence, String[] posTags, String[] lemmas,
                String word, String[] senseIDs) {
    setSentence(sentence);
    setPosTags(posTags);
    setLemmas(lemmas);
    setSenseIDs(senseIDs);

    for (int i = 0; i < sentence.length; i++) {
      if (word.equals(sentence[i])) {
        setWordIndex(i);
        break;
      }
    }
  }

  public WTDIMS(WSDSample s) {
    this(s.getSentence(), s.getTags(), s.getLemmas(), s.getTargetPosition(), s.getSenseIDs());
  }

  public String[] getSentence() {
    return sentence;
  }

  public void setSentence(String[] sentence) {
    this.sentence = sentence;
  }

  public String[] getPosTags() {
    return posTags;
  }

  public void setPosTags(String[] posTags) {
    this.posTags = posTags;
  }

  public int getWordIndex() {
    return wordIndex;
  }

  public void setWordIndex(int wordIndex) {
    this.wordIndex = wordIndex;
  }

  public String[] getLemmas() {
    return lemmas;
  }

  public void setLemmas(String[] lemmas) {
    this.lemmas = lemmas;
  }

  public int getSense() {
    return sense;
  }

  public void setSense(int sense) {
    this.sense = sense;
  }

  public String[] getSenseIDs() {
    return senseIDs;
  }

  public void setSenseIDs(String[] senseIDs) {
    this.senseIDs = senseIDs;
  }

  public String getWord() {
    return this.getSentence()[this.getWordIndex()];
  }

  public String getWordTag() {

    String wordBaseForm = lemmas[wordIndex];

    String ref = "";

    POS pos = WSDHelper.getPOS(posTags[wordIndex]);
    if (pos != null) {
      if (pos.equals(POS.VERB)) {
        ref = wordBaseForm + ".v";
      } else if (pos.equals(POS.NOUN)) {
        ref = wordBaseForm + ".n";
      } else if (pos.equals(POS.ADJECTIVE)) {
        ref = wordBaseForm + ".a";
      } else if (pos.equals(POS.ADVERB)) {
        ref = wordBaseForm + ".r";
      }
    }

    return ref;
  }

  public String[] getPosOfSurroundingWords() {
    return posOfSurroundingWords;
  }

  public void setPosOfSurroundingWords(String[] posOfSurroundingWords) {
    this.posOfSurroundingWords = posOfSurroundingWords;
  }

  public String[] getSurroundingWords() {
    return surroundingWords;
  }

  public void setSurroundingWords(String[] surroundingWords) {
    this.surroundingWords = surroundingWords;
  }

  public String[] getLocalCollocations() {
    return localCollocations;
  }

  public void setLocalCollocations(String[] localCollocations) {
    this.localCollocations = localCollocations;
  }

  public String[] getFeatures() {
    return this.features;
  }

  public void setFeatures(String[] features) {
    this.features = features;
  }

}
