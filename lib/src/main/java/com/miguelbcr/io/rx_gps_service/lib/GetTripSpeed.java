/*
 * Copyright 2016 miguelbcr
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

package com.miguelbcr.io.rx_gps_service.lib;

import rx.Observable;

class GetTripSpeed {
    private float speed;
    private long time, distance;
    private float discardSpeedsAbove;

    GetTripSpeed() {
    }

    void setParams(long distance, long time, float discardSpeedsAbove) {
        this.distance = distance;
        this.time = time;
        this.discardSpeedsAbove = discardSpeedsAbove;
    }

    long getLastTimeElapsed() {
        return time;
    }

    float getSpeed() {
        return speed;
    }

    Observable<Float> builtObservable() {
        speed = time == 0 ? 0 : 1f * distance / time;

        if (discardSpeedsAbove > 0 && speed > discardSpeedsAbove)
            speed = 0;

        return Observable.just(speed);
    }
}
