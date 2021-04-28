/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.huawei.hms.audiokitdemo.utils;

/**
 * Function Description
 *
 * @since 2021 -01-22
 */
public final class MathUtils {
    /**
     * Parse int int.
     *
     * @param str    the str
     * @param defInt the def int
     * @return the int
     */
    public static int parseInt(String str, int defInt) {
        return parseInt(str, 10, defInt);
    }

    /**
     * Parse int int.
     *
     * @param str    the str
     * @param radix  the radix
     * @param defInt the def int
     * @return the int
     */
    public static int parseInt(String str, int radix, int defInt) {
        if (StringUtils.isEmpty(str)) {
            return defInt;
        }

        try {
            return Integer.parseInt(str, radix);
        } catch (NumberFormatException e) {
            return defInt;
        }
    }
}
