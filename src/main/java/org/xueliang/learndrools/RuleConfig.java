package org.xueliang.learndrools;


import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * @author xueliang
 * @since 2020-04-08 19:27
 */
@Getter
@Setter
@XmlRootElement(name = "rule-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class RuleConfig {

    @XmlElement
    private String title;

    @XmlElement
    private String description;

    @XmlElement(name = "rule-list")
    private RuleList ruleList;
}
