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

package opennlp.tools.disambiguator;

import opennlp.tools.util.eval.Evaluator;
import opennlp.tools.util.eval.Mean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link WSDEvaluator} measures the performance of the given
 * {@link Disambiguator} with the provided reference {@code WordToDisambiguate}.
 *
 * @see Evaluator
 * @see Disambiguator
 * @see AbstractWSDisambiguator
 */
public class WSDEvaluator extends Evaluator<WSDSample> {

  private static final Logger LOG = LoggerFactory.getLogger(WSDEvaluator.class);

  private final Mean accuracy = new Mean();

  /**
   * The {@link Disambiguator} used to create the disambiguated senses.
   */
  private final Disambiguator disambiguator;

  /**
   * Initializes the current instance with the given {@link AbstractWSDisambiguator}.
   *
   * @param disambiguator
   *          the {@link AbstractWSDisambiguator} to evaluate.
   * @param listeners
   *          evaluation sample listeners
   */
  public WSDEvaluator(Disambiguator disambiguator, WSDEvaluationMonitor... listeners) {
    super(listeners);
    this.disambiguator = disambiguator;
  }

  @Override
  protected WSDSample processSample(WSDSample ref) {

    String[] referenceSenses = ref.getSenseIDs();

    // get the best predicted sense
    String predictedSense = disambiguator.disambiguate(ref.getSentence(),
        ref.getTags(), ref.getLemmas(), ref.getTargetPosition());

    if (predictedSense == null) {
      LOG.debug("There was no sense for: {}", ref.getTargetWord());
      return null;
    }
    // get the senseKey from the result
    String[] parts = predictedSense.split(" ");

    if (parts.length > 1 && referenceSenses[0].equals(parts[1])) {
      accuracy.add(1);
    } else {
      accuracy.add(0);
    }

    return new WSDSample(ref.getSentence(), ref.getTags(),
        ref.getLemmas(), ref.getTargetPosition());
  }

  /**
   * Retrieves the WSD accuracy.
   * <p>
   * This is defined as: WSD accuracy = correctly disambiguated / total words
   *
   * @return the WSD accuracy
   */
  public double getAccuracy() {
    return accuracy.mean();
  }

  /**
   * Retrieves the total number of words considered in the evaluation.
   *
   * @return the word count
   */
  public long getWordCount() {
    return accuracy.count();
  }

  /**
   * Represents this objects as human-readable {@link String}.
   */
  @Override
  public String toString() {
    return "Accuracy: " + (accuracy.mean() * 100) + "%"
        + "\tNumber of Samples: " + accuracy.count();
  }
}
