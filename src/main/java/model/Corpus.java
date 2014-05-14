package model;

import java.util.Set;

/**
 * @author Pang Zineng (pang0144@e.ntu.edu.sg)
 *
 */
public class Corpus {

	public Set<String> extractCandidateFeature() {
		// TODO to be implemented, use PosTagger
		/**
		 * Using dependency parsing, we attempt to accurately generate a candidate feature set CF (CF ={cf1, . . . ,cfi , . . . ,
cfM }, M: set size), which comprises only nouns (noun phrases)
with “SBV” (subject-verb), “VOB” (verb-object), or “POB”
(preposition-object) dependency relations in the corpus C.
		 * *
		 */
		return null;
	}

	public Set<String> extractCandidateOpinion() {
		// TODO to be implemented
		return null;
	}

}
