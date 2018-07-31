package org.opensingular.singular.form.showcase.component.internal.tabletool;

import org.opensingular.internal.lib.commons.xml.ConversorToolkit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Just a entity use in the examples.
 * @author Daniel C. Bordin
 * @since 2018-07-30
 */
class MyProject {

    private final String name;
    private final Date start;
    private final Date end;
    private final BigDecimal expenses;

    private MyProject(String name, String startDate, String endDate, double expenses) {
        this.name = name;
        this.start = ConversorToolkit.getDateFromDate(startDate);
        this.end = ConversorToolkit.getDateFromDate(endDate);
        this.expenses = new BigDecimal(expenses);
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public static List<MyProject> listProjects() {
        List<MyProject> projects = new ArrayList<>();
        projects.add(new MyProject("First", "07/07/2018", "09/08/2018", 100000));
        projects.add(new MyProject("Second", "08/08/2018", null,2000000));
        projects.add(new MyProject("Third", "09/09/2018", null,540000));
        projects.add(new MyProject("Fourth", "10/10/2018", null,3401200));
        projects.add(new MyProject("Fifth", "11/11/2018", "12/11/2018", 3401200));
        projects.add(new MyProject("Sixth", "12/12/2018", null,3401200));
        return projects;
    }
}
