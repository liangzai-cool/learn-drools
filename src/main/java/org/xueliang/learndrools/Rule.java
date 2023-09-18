package org.xueliang.learndrools;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

/**
 * @author xueliang
 * @since 2020-04-01 17:00
 */
@Getter
@Setter
@XmlRootElement(name = "rule")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rule {

    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "group-id")
    private String groupId;

    @XmlElement
    private String title;

    @XmlElement
    private String description;

    @XmlElement(name = "code")
    private String code;

    @XmlElement(name = "version")
    private String version;
}
