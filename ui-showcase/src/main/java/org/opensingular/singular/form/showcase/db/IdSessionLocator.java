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
package org.opensingular.singular.form.showcase.db;

/**
 * Armazena o sessionId.
 * 
 */
public class IdSessionLocator {

    private static IdSessionLocator idSessionLocator;

    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private IdSessionLocator() {

    }

    public static IdSessionLocator get() {
        if (idSessionLocator == null) {
            idSessionLocator = new IdSessionLocator();
        }
        return idSessionLocator;
    }

    public void setSessionId(String sessionId) {
        threadLocal.set(sessionId);
    }

    public String getSessionId() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}
