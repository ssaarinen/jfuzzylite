/*   Copyright 2013 Juan Rada-Vilela

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.fuzzylite.rule;

import com.fuzzylite.FuzzyLite;
import com.fuzzylite.Op;
import com.fuzzylite.norm.SNorm;
import com.fuzzylite.norm.TNorm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class RuleBlock {

    protected String name;
    protected List<Rule> rules;
    protected TNorm conjunction;
    protected SNorm disjunction;
    protected TNorm activation;

    public RuleBlock() {
        this("");
    }

    public RuleBlock(String name) {
        this(name, null, null, null);
    }
    
    public RuleBlock(TNorm conjunction, SNorm disjunction, TNorm activation){
    	this("", conjunction, disjunction, activation);
    }
    
    public RuleBlock(String name, TNorm conjunction, SNorm disjunction, TNorm activation) {
        this.name = name;
        this.conjunction = conjunction;
        this.disjunction = disjunction;
        this.activation = activation;
        this.rules = new ArrayList<>();
    }

    public void activateRules() {
        for (Rule rule : rules) {
            double activationDegree = rule.activationDegree(conjunction, disjunction);
            FuzzyLite.logger().info(String.format("%s [degree=%f]", rule.toString(), activationDegree));
            if (Op.isGt(activationDegree, 0.0)) {
                rule.modifyConsequent(activationDegree, activation);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public RuleBlock addRule(Rule rule){
    	this.rules.add(rule);
    	return this;
    }
    
    public TNorm getConjunction() {
        return conjunction;
    }

    public void setConjunction(TNorm conjunction) {
        this.conjunction = conjunction;
    }

    public SNorm getDisjunction() {
        return disjunction;
    }

    public void setDisjunction(SNorm disjunction) {
        this.disjunction = disjunction;
    }

    public TNorm getActivation() {
        return activation;
    }

    public void setActivation(TNorm activation) {
        this.activation = activation;
    }
}