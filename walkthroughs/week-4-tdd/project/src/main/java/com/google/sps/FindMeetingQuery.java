// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.lang.Math;

public final class FindMeetingQuery {
    
    /**
     * Return available time period for a meeting request
     *
     * @param events All scheduled meetings. Can be null.
     * @param request A new meeting request, including duration and attendees. 
     *                Must be non-null.
     */
    public Collection<TimeRange> query(Collection<Event> events, 
                                       MeetingRequest request) {
        if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
            return Arrays.asList();
        }
        
        Collection<String> attendees = request.getAttendees();
        if (attendees.isEmpty()) {
            return Arrays.asList(TimeRange.WHOLE_DAY);
        }

        ArrayList<TimeRange> attendeeMeetingTimeRanges = 
            getAttendeeMeetingTimeRanges(events, attendees);
        Collections.sort(attendeeMeetingTimeRanges, TimeRange.ORDER_BY_START);

        ArrayList<TimeRange> availableTimeRanges = new ArrayList<TimeRange>();
        int start = TimeRange.START_OF_DAY;
        int idx = 0;
        while (idx < attendeeMeetingTimeRanges.size()) {
            while ((idx < attendeeMeetingTimeRanges.size()) && 
                (start >= attendeeMeetingTimeRanges.get(idx).start())) {
                start = Math.max(start, 
                    attendeeMeetingTimeRanges.get(idx).end());
                idx++;
            }
            if (idx >= attendeeMeetingTimeRanges.size()) break;

            int end = attendeeMeetingTimeRanges.get(idx).start();
            if (end - start >= request.getDuration()) {
                availableTimeRanges.add(
                    TimeRange.fromStartEnd(start, end, false));
            }
            start = attendeeMeetingTimeRanges.get(idx).end();

            idx++;
        }
        if ((start < TimeRange.END_OF_DAY) && 
            (TimeRange.END_OF_DAY - start >= request.getDuration())) {
            availableTimeRanges.add(
                TimeRange.fromStartEnd(start, TimeRange.END_OF_DAY, true));
        }

        return availableTimeRanges;
    }

    private ArrayList<TimeRange> getAttendeeMeetingTimeRanges(
        Collection<Event> events, 
        Collection<String> attendees) {
        ArrayList<TimeRange> attendeeMeetingTimeRanges = 
            new ArrayList<TimeRange>();

        for (Event e : events) {
            for (String attendee: attendees) {
                if (e.getAttendees().contains(attendee)) {
                    attendeeMeetingTimeRanges.add(e.getWhen());
                    break;
                }
            }
        }
        
        return attendeeMeetingTimeRanges;
    }
}
