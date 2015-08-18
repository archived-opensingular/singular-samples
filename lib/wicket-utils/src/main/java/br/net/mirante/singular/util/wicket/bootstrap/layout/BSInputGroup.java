package br.net.mirante.singular.util.wicket.bootstrap.layout;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import br.net.mirante.singular.util.wicket.resource.Icone;

public class BSInputGroup extends BSControls {

    public BSInputGroup(String id) {
        super(id);
        add(new AttributeAppender("class", "input-group", " "));
    }

    public BSInputGroup appendCheckboxAddon(Component checkbox) {
        return appendTag("div", true, "class='input-group-addon'", id -> new BSContainer<>(id)
            .appendTag("input", false, "type='checkbox'", checkbox));
    }

    public BSInputGroup appendIconAddon(Icone icone) {
        return appendTag("div", true, "class='input-group-addon'", id -> new BSContainer<>(id)
            .appendTag("i", true, "class='" + icone.getCssClass() + "'", new WebMarkupContainer("i")));
    }

    public BSInputGroup appendTextAddon(IModel<String> text) {
        return appendTag("div", true, "class='input-group-addon'", id -> new Label(id, text));
    }

    public BSInputGroup appendButtonGroup(IBSComponentFactory<BSInputGroupButton> factory) {
        return appendComponent(factory);
    }
    public BSInputGroupButton newButtonGroup() {
        return newComponent(BSInputGroupButton::new);
    }

    @Override
    public BSInputGroup appendTag(String tag, Component component) {
        return (BSInputGroup) super.appendTag(tag, component);
    }
    @Override
    public BSInputGroup appendTag(String tag, boolean closeTag, String attrs, Component component) {
        return (BSInputGroup) super.appendTag(tag, closeTag, attrs, component);
    }
    @Override
    public BSInputGroup appendTag(String tag, boolean closeTag, String attrs, IBSComponentFactory<Component> factory) {
        return (BSInputGroup) super.appendTag(tag, closeTag, attrs, factory);
    }
    @Override
    public <C extends Component> BSInputGroup appendComponent(IBSComponentFactory<C> factory) {
        return (BSInputGroup) super.appendComponent(factory);
    }
    @Override
    public BSInputGroup xs(int colspan) {
        return (BSInputGroup) super.xs(colspan);
    }
    @Override
    public BSInputGroup sm(int colspan) {
        return (BSInputGroup) super.sm(colspan);
    }
    @Override
    public BSInputGroup md(int colspan) {
        return (BSInputGroup) super.md(colspan);
    }
    @Override
    public BSInputGroup lg(int colspan) {
        return (BSInputGroup) super.lg(colspan);
    }
    @Override
    public BSInputGroup xsOffset(int colspan) {
        return (BSInputGroup) super.xsOffset(colspan);
    }
    @Override
    public BSInputGroup smOffset(int colspan) {
        return (BSInputGroup) super.smOffset(colspan);
    }
    @Override
    public BSInputGroup mdOffset(int colspan) {
        return (BSInputGroup) super.mdOffset(colspan);
    }
    @Override
    public BSInputGroup lgOffset(int colspan) {
        return (BSInputGroup) super.lgOffset(colspan);
    }
    @Override
    public BSInputGroup xsHidden(boolean hidden) {
        return (BSInputGroup) super.xsHidden(hidden);
    }
    @Override
    public BSInputGroup smHidden(boolean hidden) {
        return (BSInputGroup) super.smHidden(hidden);
    }
    @Override
    public BSInputGroup mdHidden(boolean hidden) {
        return (BSInputGroup) super.mdHidden(hidden);
    }
    @Override
    public BSInputGroup lgHidden(boolean hidden) {
        return (BSInputGroup) super.lgHidden(hidden);
    }
    @Override
    public BSInputGroup appendCheckbox(Component checkbox) {
        return (BSInputGroup) super.appendCheckbox(checkbox);
    }
    @Override
    public BSInputGroup appendCheckbox(Component checkbox, IModel<?> labelModel) {
        return (BSInputGroup) super.appendCheckbox(checkbox, labelModel);
    }
    @Override
    public BSInputGroup appendCheckbox(Component checkbox, Component label) {
        return (BSInputGroup) super.appendCheckbox(checkbox, label);
    }
    @Override
    public BSInputGroup appendInputEmail(Component input) {
        return (BSInputGroup) super.appendInputEmail(input);
    }
    @Override
    public BSInputGroup appendInputPassword(Component input) {
        return (BSInputGroup) super.appendInputPassword(input);
    }
    @Override
    public BSInputGroup appendInputText(Component input) {
        return (BSInputGroup) super.appendInputText(input);
    }
    @Override
    public BSInputGroup appendTypeahead(Component typeahead) {
        return (BSInputGroup) super.appendTypeahead(typeahead);
    }
    @Override
    public BSInputGroup appendInputButton(Component button) {
        return (BSInputGroup) super.appendInputButton(button);
    }
    @Override
    public BSInputGroup appendInputButton(String extraClasses, Component button) {
        return (BSInputGroup) super.appendInputButton(extraClasses, button);
    }
}
