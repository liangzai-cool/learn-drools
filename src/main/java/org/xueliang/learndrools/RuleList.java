package org.xueliang.learndrools;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "rule-list")
@XmlAccessorType(XmlAccessType.FIELD)
public class RuleList {

    @XmlElement(name = "rule")
    private List<Rule> ruleList;
}