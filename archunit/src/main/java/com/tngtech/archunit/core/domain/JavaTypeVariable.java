/*
 * Copyright 2014-2020 TNG Technology Consulting GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tngtech.archunit.core.domain;

import java.util.List;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.core.importer.DomainBuilders.JavaTypeVariableBuilder;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

/**
 * Represents a type variable used by generic types and members.<br>
 * E.g. {@code class MyClass<T>} would have one {@link JavaTypeVariable} with name "T"
 * and unbound, i.e. only bound by {@link Object}.<br>
 * A type variable can have several bounds, where only one bound may be a class bound
 * while all further bounds must be interfaces (compare the JLS).<br>
 * Example: {@code class MyClass<T extends SomeClass & SomeInterfaceOne & SomeInterfaceTwo>}
 * would declare one {@link JavaTypeVariable} {@code T} which is bound by {@code SomeClass},
 * {@code SomeInterfaceOne} and {@code SomeInterfaceTwo}. I.e. any concrete class
 * substituted for the type variable must extend {@code SomeClass} and implement
 * {@code SomeInterfaceOne} and {@code SomeInterfaceTwo}.
 */
@PublicAPI(usage = ACCESS)
public final class JavaTypeVariable implements JavaType {
    private final String name;
    private final List<JavaType> bounds;

    JavaTypeVariable(JavaTypeVariableBuilder builder) {
        name = builder.getName();
        bounds = builder.getBounds();
    }

    /**
     * @return The name of this {@link JavaTypeVariable}, e.g. for  {@code class MyClass<T>}
     *         the name would be "T"
     */
    @Override
    @PublicAPI(usage = ACCESS)
    public String getName() {
        return name;
    }

    /**
     * @return All bounds of this {@link JavaTypeVariable}, i.e. super types any substitution
     *         of this variable must extend. E.g. for
     *         {@code class MyClass<T extends SomeClass & SomeInterface>} the bounds would be
     *         {@code SomeClass} and {@code SomeInterface}
     */
    @PublicAPI(usage = ACCESS)
    public List<JavaType> getBounds() {
        return bounds;
    }
}
