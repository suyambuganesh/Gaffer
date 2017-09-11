/*
 * Copyright 2017 Crown Copyright
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

package uk.gov.gchq.gaffer.serialisation.implementation.ordered;

import uk.gov.gchq.gaffer.serialisation.DelegateSerialiser;

public class OrderedFloatSerialiser extends DelegateSerialiser<Float, Integer> {

    private static final long serialVersionUID = 6829577492677279853L;
    private static final OrderedIntegerSerialiser INTEGER_SERIALISER = new OrderedIntegerSerialiser();

    public OrderedFloatSerialiser() {
        super(INTEGER_SERIALISER);
    }

    @Override
    public Float fromDelegateType(final Integer object) {
        int i = object;
        if (i < 0) {
            i = i ^ 0x80000000;
        } else {
            i = ~i;
        }
        return Float.intBitsToFloat(i);
    }

    @Override
    public Integer toDelegateType(final Float object) {
        int i = Float.floatToRawIntBits(object);
        if (i < 0) {
            i = ~i;
        } else {
            i = i ^ 0x80000000;
        }
        return i;
    }

    @Override
    public boolean isConsistent() {
        return true;
    }

    @Override
    public boolean canHandle(final Class clazz) {
        return Float.class.equals(clazz);
    }
}