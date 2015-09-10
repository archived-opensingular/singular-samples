package br.net.mirante.singular.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.core.request.ClientInfo;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

public class UIAdminSession extends Session {

    private String name;
    private String avatar;
    private String logout;

    public UIAdminSession(Request request, @SuppressWarnings("UnusedParameters") Response response) {
        super(request);
        this.name = request.getRequestParameters().getParameterValue("name").toString("Admin");
        this.avatar = request.getRequestParameters().getParameterValue("avatar").toString(null);
        this.logout = request.getRequestParameters().getParameterValue("logout").toString(null);
    }

    public static UIAdminSession get() {
        return (UIAdminSession) Session.get();
    }

    @Override
    public ClientInfo getClientInfo() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
}