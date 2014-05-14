package lib.abb;

import java.util.HashSet;
import java.util.Set;

import model.Corpus;

/**
 * Container of the Association-based bootstrapping algorithm
 * 
 * @author  Pang Zineng (pang0144@e.ntu.edu.sg)
 */
public class ABOOT {
	
	/** thresholds for the Feature-Opinion associations */
	public static final int foth = 0;
	/** thresholds for the Feature-Feature associations */
	public static final int ffth = 0;
	/** thresholds for the Opinion-Opinion associations */
	public static final int ooth = 0;
	
	private Set<String> featureSet;
	private Set<String> opinionSet;

	public Set<String> getFeatureSet() {
		return featureSet;
	}

	public void setFeatureSet(Set<String> featureSet) {
		this.featureSet = featureSet;
	}

	public Set<String> getOpinionSet() {
		return opinionSet;
	}

	public void setOpinionSet(Set<String> opinionSet) {
		this.opinionSet = opinionSet;
	}

	public ABOOT() {
		featureSet = new HashSet<String>();
		opinionSet = new HashSet<String>();
	}
	
	/**
	 * @param c review corpus
	 * @param s labeled feature seed set
	 */
	public void extract(Corpus c, Set<String> s){
		
		/** extract candidate from corpus*/
		Set<String> cf = c.extractCandidateFeature();
		Set<String> co = c.extractCandidateOpinion();
		
		/** initialize result set*/
		featureSet.addAll(s);
		
		Set<String> newFeatureSet = new HashSet<String>();
		Set<String> newOpinionSet = new HashSet<String>();
		
		/** Continue until No new opinion features are identified */
		do {
			newFeatureSet.clear();
			newOpinionSet.clear();

			/** Loop through identified feature */
			for(String knowFeature : featureSet){
				for(String candidateFeature : cf){
					if(!featureSet.contains(candidateFeature) && 
							ASE.score(knowFeature, candidateFeature) >= ffth){ 
						newFeatureSet.add(candidateFeature);
						cf.remove(candidateFeature);
					}
				}
				for(String candidateOpinion : co){
					if(!opinionSet.contains(candidateOpinion) &&
							ASE.score(knowFeature, candidateOpinion) >= foth){
						newOpinionSet.add(candidateOpinion);
						co.remove(candidateOpinion);
					}
				}
			}
			
			/** Loop through identified opinion */
			for(String knowOpinion : opinionSet){
				for(String candidateOpinion : co){
					if(!opinionSet.contains(candidateOpinion) &&
							ASE.score(knowOpinion, candidateOpinion) >= ooth){
						newOpinionSet.add(candidateOpinion);
						co.remove(candidateOpinion);
					}
				}
				for(String candidateFeature : cf){
					if(!featureSet.contains(candidateFeature) &&
							ASE.score(knowOpinion, candidateFeature) >= foth){
						newFeatureSet.add(candidateFeature);
						cf.remove(candidateFeature);
					}
				}
			}
			
			/** Update identified feature and opinion */
			featureSet.addAll(newFeatureSet);
			opinionSet.addAll(newOpinionSet);

		} while (!newFeatureSet.isEmpty() || !newOpinionSet.isEmpty());
	}
}
