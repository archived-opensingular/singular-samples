package br.net.mirante.singular.commons.base;

import static br.net.mirante.singular.commons.base.SingularPropertiesTest.*;

import org.junit.Assert;
import org.junit.Test;

public class SingularPropertiesImplTest {

    @Test
    public void testTester() {
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));
        SingularPropertiesImpl.Tester.runInSandbox(impl -> {
            impl.setProperty(MOCK_PROPERTY_KEY, "abc");
            Assert.assertEquals("abc", SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));
        });
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));
    }

    @Test
    public void testSaveRestore() {
        final String originalServerHome = SingularProperties.get().getSingularServerHome();
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));

        Object state1 = SingularPropertiesImpl.Tester.saveState();
        Assert.assertEquals(originalServerHome, SingularProperties.get().getSingularServerHome());
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));
        
        SingularPropertiesImpl.get().setSingularServerHome("/");
        Assert.assertEquals("/", SingularProperties.get().getSingularServerHome());
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));

        Object state2 = SingularPropertiesImpl.Tester.saveState();
        Assert.assertEquals("/", SingularProperties.get().getSingularServerHome());
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));
        
        SingularPropertiesImpl.get().setProperty(MOCK_PROPERTY_KEY, "xxx");
        Assert.assertEquals("/", SingularProperties.get().getSingularServerHome());
        Assert.assertEquals("xxx", SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));

        SingularPropertiesImpl.Tester.restoreState(state2);
        Assert.assertEquals("/", SingularProperties.get().getSingularServerHome());
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));

        SingularPropertiesImpl.Tester.restoreState(state1);
        Assert.assertEquals(originalServerHome, SingularProperties.get().getSingularServerHome());
        Assert.assertEquals(MOCK_PROPERTY_CLASSPATH_VALUE, SingularProperties.get().getProperty(MOCK_PROPERTY_KEY));
    }
}