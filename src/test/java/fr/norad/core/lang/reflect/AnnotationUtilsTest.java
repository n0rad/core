/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package fr.norad.core.lang.reflect;

import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AnnotationUtilsTest {

    interface ParameterInterface {
        public UUID getUUID(@Deprecated UUID uid);
    }

    interface ParameterInterface2 extends ParameterInterface {
    }

    class ParamaterAbstract implements ParameterInterface2 {
        @Override
        public UUID getUUID(UUID uid) {
            return null;
        }
    }

    class ParameterClass extends ParamaterAbstract {
        @Override
        public UUID getUUID(UUID uid) {
            return super.getUUID(uid);
        }
    }

    @Test
    public void should_find_annotation() throws Exception {
        Deprecated annotation = AnnotationUtils.findParameterAnnotation(
                ParameterClass.class.getMethod("getUUID", UUID.class), 0, Deprecated.class);

        Assertions.assertThat(annotation).isNotNull();
    }

}
