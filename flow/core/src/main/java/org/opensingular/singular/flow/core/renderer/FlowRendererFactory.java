/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.flow.core.renderer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.opensingular.singular.flow.core.Flow;
import org.opensingular.singular.flow.core.ProcessDefinition;

import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class FlowRendererFactory {

    @SuppressWarnings("rawtypes")
    private static final LoadingCache<Class<? extends ProcessDefinition>, byte[]> cache =
        CacheBuilder.newBuilder().expireAfterWrite(4, TimeUnit.HOURS)
            .build(new CacheLoader<Class<? extends ProcessDefinition>, byte[]>() {
                @Override
                public byte[] load(Class<? extends ProcessDefinition> classe) throws Exception {
                    return flowRenderer().generateImage(Flow.getProcessDefinition(classe));
                }

                private IFlowRenderer flowRenderer() {
                    return Flow.getConfigBean().getFlowRenderer();
                }
            });

    public static byte[] generateImageFor(ProcessDefinition<?> processDefinition) {
        try {
            return cache.get(processDefinition.getClass());
        } catch (ExecutionException e) {
            throw Throwables.propagate(e);
        }
    }
    
    /**
     * Discards all entries in the cache.
     */
    public static synchronized void invalidateCache() {
        cache.invalidateAll();
    }
}