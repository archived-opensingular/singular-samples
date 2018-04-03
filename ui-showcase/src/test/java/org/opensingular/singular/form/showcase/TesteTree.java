package org.opensingular.singular.form.showcase;

import org.json.JSONObject;
import org.junit.Test;
import org.opensingular.form.wicket.mapper.tree.TreeNode;
import org.opensingular.singular.form.showcase.component.form.core.search.Processo;
import org.opensingular.singular.form.showcase.component.form.core.search.ProcessoRepository;

import java.util.ArrayList;
import java.util.List;

public class TesteTree {

    @Test
    public void testeArvore() {
        ProcessoRepository repository = new ProcessoRepository();
        List<Processo> processos = repository.get(null);
        processos.forEach(TesteTree::printarArvore);
    }

    @Test
    public void testeJsonElement() {
        ProcessoRepository repository = new ProcessoRepository();
        List<Processo> processos = repository.get(null);
        processos.forEach(p -> {
            JSONObject jsonObject = populateJson(p);
            System.out.println(jsonObject.toString());
        });
    }

    private JSONObject populateJson(Processo p) {
        JSONObject json = new JSONObject();
        if (!p.isLeaf()) {
            List<JSONObject> childs = new ArrayList<>();
            p.getChildrens().forEach(pc -> childs.add(populateJson(pc)));
            json.put("children", childs);
        }
        json.put("id", p.getNumeroProcesso());
        json.put("text", p.getDescricao());
        return json;
    }

    private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }


    private static void printarArvore(TreeNode<? extends TreeNode> treeNode) {
        if (treeNode.isLeaf()) {
            System.out.println(createIndent(treeNode.getLevel()) + " " + treeNode.getDisplayLabel());
        } else {
            System.out.println(createIndent(treeNode.getLevel()) + " " + treeNode.getDisplayLabel());
            treeNode.getChildrens().forEach(TesteTree::printarArvore);
        }
    }
}
