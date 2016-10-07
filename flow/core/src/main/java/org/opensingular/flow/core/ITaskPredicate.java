/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.flow.core;

import java.io.Serializable;
import java.util.function.Predicate;

public interface ITaskPredicate extends Predicate<TaskInstance>, Serializable {

    String getName();

    EventType getEventType();

    default String getFullDescription() {
        return getName();
    }

    default String getDescription(TaskInstance taskInstance) {
        return getFullDescription();
    }
}
