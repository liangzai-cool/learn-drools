package org.xueliang.learndrools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

public class App {

    public static void main(String[] args) throws JAXBException {
        DroolsProcessor droolsProcessor = new DroolsProcessor();
        String path = ClassUtil.getResourceURL("rule-config.xml").getPath();
        String content = FileUtil.readUtf8String(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(RuleConfig.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        RuleConfig ruleRoot = (RuleConfig) unmarshaller.unmarshal(new StringReader(content));
        droolsProcessor.reload(ruleRoot.getRuleList().getRuleList());
        List<Rule> ruleList0 = droolsProcessor.matchRule("group-id-1", "xx");
        System.out.println("ruleList0.size: " + ruleList0.size());
        System.out.println("=======================================================");
        List<Rule> ruleList1 = droolsProcessor.matchRule("group-id-2", "xx");
        System.out.println("ruleList1.size: " + ruleList1.size());
    }
}
