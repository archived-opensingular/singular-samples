package br.net.mirante.singular.flow.core;

import java.util.function.Supplier;

import com.google.common.base.Preconditions;

import br.net.mirante.singular.flow.schedule.IScheduleData;
import br.net.mirante.singular.flow.schedule.IScheduledJob;
import br.net.mirante.singular.flow.schedule.ScheduleDataBuilder;

public class ProcessScheduledJob implements IScheduledJob {

    private final FlowMap mapa;

    private final String name;

    private Supplier<Object> job;

    private IScheduleData scheduleData;

    ProcessScheduledJob(FlowMap mapa, String name) {
        Preconditions.checkNotNull(name);
        this.mapa = mapa;
        this.name = name;
    }

    public ProcessScheduledJob call(Supplier<Object> impl) {
        this.job = impl;
        return this;
    }

    public ProcessScheduledJob call(Runnable impl) {
        return call(() -> {
            impl.run();
            return null;
        });
    }

    public Object run() {
        Preconditions.checkNotNull(job, "Job implementation not provided.");
        return job.get();
    }

    public ProcessScheduledJob withMonthlySchedule(int dayOfMonth, int hours, int minutes, Integer... months) {
        return withSchedule(ScheduleDataBuilder.buildMonthly(dayOfMonth, hours, minutes, months));
    }

    public ProcessScheduledJob withDailySchedule(int hora, int minuto) {
        return withSchedule(ScheduleDataBuilder.buildDaily(hora, minuto));
    }

    public ProcessScheduledJob withSchedule(IScheduleData scheduleData) {
        Preconditions.checkArgument(this.scheduleData == null, "Job already scheduled.");
        this.scheduleData = scheduleData;
        return this;
    }

    public String getId() {
        return mapa.getDefinicaoProcesso().getSigla() + "::" + getName() + "()";
    }

    public IScheduleData getScheduleData() {
        return scheduleData;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProcessScheduledJob [job=" + getId() + ", scheduleData=" + scheduleData + "]";
    }
}