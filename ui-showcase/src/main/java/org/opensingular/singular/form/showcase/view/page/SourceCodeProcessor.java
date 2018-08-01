/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.singular.form.showcase.view.page;

import org.apache.commons.lang3.StringUtils;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class SourceCodeProcessor {

    private final String sourceCode;
    private final List<String> classJavadoc = new ArrayList<>();
    private final List<Predicate<String>> lineToBeIgnoredConditions = new ArrayList<>();
    private final List<Line> source = new ArrayList<>();

    private boolean highLightBlockOn = false;
    private boolean highLightNextLine = false;

    public SourceCodeProcessor(String sourceCode) {
        this.sourceCode = sourceCode;
        loadIgnoreLines();
        splitSourceAndJavadoc();
        removeLinesBeforePackage();
        removeHiddenBlocks();
        removeIgnoreLines();
        removeCaseItemAnnotation();
    }

    private void removeCaseItemAnnotation() {
        String target = "@" + CaseItem.class.getSimpleName();
        for (int linIni = 0; linIni < source.size(); linIni++) {
            int pos = source.get(linIni).getLine().indexOf(target);
            if (pos != -1) {
                int linEnd = findLastLineClosingAnnotation(linIni);
                removeSourceLines(linIni, linEnd + 1);
                return;
            }
        }
    }

    private int findLastLineClosingAnnotation(int linIni) {
        int parenthesis = 0;
        for(int linEnd = linIni; linEnd < source.size(); linEnd++) {
            parenthesis = countParenthesis(linEnd, parenthesis);
            if (parenthesis == 0) {
                return linEnd;
            }
        }
        return source.size() - 1;
    }

    private int countParenthesis(int lin, int parenthesis) {
        int count = parenthesis;
        String line = source.get(lin).getLine();
        for (int p = 0; p < line.length(); p++) {
            if (line.charAt(p) == ')') {
                count--;
                if (count == 0) {
                    return 0;
                }
            } else if (line.charAt(p) == '(') {
                count++;
            }
        }
        return count;
    }

    private void splitSourceAndJavadoc() {
        boolean javadoc = false;
        boolean classStarted = false;
        final String[] lines = sourceCode.split("\n");

        for (int i = 0; i < lines.length; i += 1) {
            final String line = lines[i];
            if (javadoc) {
                javadoc = processJavaDoc(line);
                continue;
            } else if (!classStarted) {
                if (line.startsWith("/**")) {
                    javadoc = true;
                    continue;
                }
                classStarted = isClassBegin(line);
            }
            analyzeSourceLines(lines, i, line);
        }
    }

    private boolean isClassBegin(String line) {
        return line.contains("public class ");
    }

    private void removeLinesBeforePackage() {
        int packageLine = findLine(0, line -> line.isStartWith("package"));
        if (packageLine > 0) {
            removeSourceLines(0, packageLine);
        }
    }

    private void removeHiddenBlocks() {
        int i = 0;
        while (i < source.size()) {
            int pos = findLine(i, line -> line.isStartWith("//hidden:begin"));
            if (pos != -1) {
                int posF = findLine(pos + 1, line -> line.isStartWith("//hidden:end"));
                if (posF == -1) {
                    removeSourceLines(pos, source.size());
                } else {
                    removeSourceLines(pos, posF + 1);
                }
                removeDoubleBlankLine(pos);
                i = pos;
            } else {
                i++;
            }
        }
    }

    private void removeIgnoreLines() {
        int i = 0;
        while (i < source.size()) {
            if (isLineToBeIgnored(source.get(i).getLine())) {
                removeSourceLines(i, i + 1);
                removeDoubleBlankLine(i);
            } else {
                i++;
            }
        }
    }

    private void removeDoubleBlankLine(int pos) {
        if (pos > 0 && isBlankLine(pos) && isBlankLine(pos - 1)) {
            removeSourceLines(pos, pos + 1);
        }
    }

    private boolean isBlankLine(int pos) {
        return pos >= 0 && StringUtils.isBlank(source.get(pos).getLine());
    }

    private int findLine(int begin, Predicate<Line> condition) {
        for (int i = begin; i < source.size(); i++) {
            if (condition.test(source.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private void analyzeSourceLines(String[] lines, int i, String line) {
        if (isHighlightBlockBegin(line)) {
            highLightBlockOn = true;
        } else if (highLightBlockOn && isHighlightBlockEnd(line)) {
            highLightNextLine = false;
            highLightBlockOn = false;
        } else {
            int pos = getHighlightStartComment(line);
            if (pos == -1) {
                source.add(new Line(lines[i], highLightBlockOn || highLightNextLine));
                highLightNextLine = false;
            } else if (pos == 0 || StringUtils.isBlank(line.substring(0, pos))) {
                highLightNextLine = true;
            } else {
                source.add(new Line(line.substring(0, pos), true));
            }
        }
    }

    private boolean processJavaDoc(String line) {
        if (line.contains("*/")) {
            return false;
        } else {
            String l = line;
            if (line.startsWith(" *")) {
                l = line.length() == 2 ? "" : line.substring(2);
            } else if (line.startsWith("*")) {
                l = line.length() == 1 ? "" : line.substring(1);
            }
            if (!l.trim().startsWith("@")) {
                classJavadoc.add(l);
            }
        }
        return true;
    }

    private boolean isHighlightBlockBegin(String candidate) {
        return candidate.contains("//@destacar:bloco") || candidate.contains("// @destacar:bloco");
    }

    private boolean isHighlightBlockEnd(String candidate) {
        return candidate.contains("//@destacar:fim") || candidate.contains("// @destacar:fim");
    }

    private int getHighlightStartComment(String line) {
        int pos = line.indexOf("//@destacar");
        if (pos == -1) {
            pos = line.indexOf("// @destacar");
        }
        return pos;
    }

    public String getResultSourceCode() {
        return source.stream().map(Line::getLine).collect(Collectors.joining("\n"));
    }

    public String getJavadoc() {
        StringBuilder sb = new StringBuilder();
        for (String line : classJavadoc) {
            if (!line.trim().startsWith("@")) {
                sb.append(line).append('\n');
            }
        }
        return sb.toString();
    }

    public List<Integer> getLinesToBeHighlighted() {
        List<Integer> highlight = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            if (source.get(i).isHighlighted()) {
                highlight.add(i + 1);
            }
        }
        return highlight;
    }

    private void removeSourceLines(int begin, int endExcluding) {
        for (int i = endExcluding - 1; i >= begin; i--) {
            source.remove(i);
        }
    }

    private boolean isLineToBeIgnored(String candidate) {
        return lineToBeIgnoredConditions.stream().anyMatch(condition -> condition.test(candidate));
    }

    private void loadIgnoreLines() {
        addIgnoreImport(CaseItem.class);
        addIgnoreImport(Group.class);
        addIgnoreImport(Resource.class);
        addIgnoreLineContaining("@formatter");
        addIgnoreLineContaining("TODO");
        addIgnoreLineContaining("/*hidden*/");
    }

    private void addIgnoreImport(Class<?> c) {
        addIgnoreLineStartingWith("import " + c.getName());
    }

    private void addIgnoreLineStartingWith(String s) {
        lineToBeIgnoredConditions.add(line -> line.trim().startsWith(s));
    }

    private void addIgnoreLineContaining(String s) {
        lineToBeIgnoredConditions.add(line -> line.contains(s));
    }

    private static class Line {
        private final String line;
        private boolean highlighted;

        private Line(String line, boolean highlighted) {
            this.line = line;
            this.highlighted = highlighted;
        }

        public String getLine() {
            return line;
        }

        public boolean isStartWith(String s) {
            return line.trim().startsWith(s);
        }

        public boolean isHighlighted() {
            return highlighted;
        }

        public void setHighlighted(boolean highlighted) {
            this.highlighted = highlighted;
        }
    }
}
