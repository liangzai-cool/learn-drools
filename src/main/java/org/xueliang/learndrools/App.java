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
        String group0Path = ClassUtil.getResourceURL("rule-list.xml").getPath();
        String group0Content = FileUtil.readUtf8String(group0Path);
        JAXBContext jaxbContext = JAXBContext.newInstance(RuleRoot.class);
        Unmarshaller ruleContainerUnmarshaller = jaxbContext.createUnmarshaller();
        RuleRoot ruleRoot = (RuleRoot) ruleContainerUnmarshaller.unmarshal(new StringReader(group0Content));
        droolsProcessor.reload(ruleRoot.getRuleList());
        List<Rule> ruleList0 = droolsProcessor.matchRule("group-id-1", "xx");
        System.out.println(ruleList0.size());
        System.out.println("=======================================================");
        List<Rule> ruleList1 = droolsProcessor.matchRule("group-id-2", "xx");
        System.out.println(ruleList1.size());
    }
}
