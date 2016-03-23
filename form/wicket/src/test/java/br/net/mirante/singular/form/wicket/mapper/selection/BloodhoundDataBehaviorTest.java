package br.net.mirante.singular.form.wicket.mapper.selection;

import br.net.mirante.singular.form.mform.*;
import br.net.mirante.singular.form.mform.basic.view.SViewAutoComplete;
import br.net.mirante.singular.form.mform.core.STypeString;
import br.net.mirante.singular.form.mform.document.RefType;
import br.net.mirante.singular.form.mform.document.SDocumentFactory;
import br.net.mirante.singular.form.mform.options.SOptionsConfig;
import br.net.mirante.singular.form.mform.options.SOptionsProvider;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.string.StringValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by nuk on 22/03/16.
 */
public class BloodhoundDataBehaviorTest {

    private WebRequest request;
    private WebResponse response;
    @Captor ArgumentCaptor<String> captor;


    @Before public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test public void setsEncoding(){
        BloodhoundDataBehavior b = createBehavior(null, null);
        b.respond(null);

        verify(response).setHeader("Content-Type", "application/json; charset=utf8");
    }

    @Test public void returnOptions(){
        SInstance instance = createInstance(createBaseType());

        BloodhoundDataBehavior b = createBehavior(null, instance.getOptionsConfig());
        b.respond(null);

        verify(response).write(captor.capture());
        JSONArray expected = new JSONArray();
        expected.put(createValue("@gmail.com"));
        expected.put(createValue("@hotmail.com"));
        expected.put(createValue("@yahoo.com"));

        JSONAssert.assertEquals(expected,new JSONArray(captor.getValue()),false);
    }

    @Test public void applyFilterToOptions(){
        SInstance instance = createInstance(createBaseType());

        BloodhoundDataBehavior b = createBehavior("bruce", instance.getOptionsConfig());
        b.respond(null);

        verify(response).write(captor.capture());
        JSONArray expected = new JSONArray();
        expected.put(createValue("bruce@gmail.com"));
        expected.put(createValue("bruce@hotmail.com"));
        expected.put(createValue("bruce@yahoo.com"));

        JSONAssert.assertEquals(expected,new JSONArray(captor.getValue()),false);
    }

    private STypeString createBaseType() {
        STypeComposite<? extends SIComposite> baseType = SDictionary.create().createNewPackage("pkg").createCompositeType("basetype");
        STypeString base = baseType.addFieldString("myHero");
        base.withSelectionFromProvider(createProvider());
        base.withView(new SViewAutoComplete(SViewAutoComplete.Mode.DYNAMIC));
        return base;
    }

    private SOptionsProvider createProvider() {
        return new SOptionsProvider() {
            @Override
            public SIList<? extends SInstance> listOptions(SInstance instance, String filter) {
                if(filter == null) filter = "";
                SIList<?> r = instance.getType().newList();
                r.addNew().setValue(filter+"@gmail.com");
                r.addNew().setValue(filter+"@hotmail.com");
                r.addNew().setValue(filter+"@yahoo.com");
                return r;
            }
        };
    }

    private SInstance createInstance(final STypeString base) {
        return SDocumentFactory.empty().createInstance(new RefType() {
            protected SType<?> retrieve() {
                return base;
            }
        });
    }

    private JSONObject createValue(String v) {
        JSONObject value = new JSONObject();
        value.put("value", v);
        return value;
    }

    private BloodhoundDataBehavior createBehavior(final String filter,
                                                  SOptionsConfig optionsProvider) {
        return new BloodhoundDataBehavior(optionsProvider){
                @Override
                protected RequestCycle requestCycle() {
                    RequestCycle cycle = Mockito.mock(RequestCycle.class);

                    request = Mockito.mock(WebRequest.class);
                    Mockito.when(cycle.getRequest()).thenReturn(request);

                    IRequestParameters params = Mockito.mock(IRequestParameters.class);
                    Mockito.when(request.getRequestParameters()).thenReturn(params);

                    Mockito.when(params.getParameterValue("filter")).thenReturn(StringValue.valueOf(filter));

                    response = Mockito.mock(WebResponse.class);
                    Mockito.when(cycle.getResponse()).thenReturn(response);

                    return cycle;
                }
            };
    }
}
