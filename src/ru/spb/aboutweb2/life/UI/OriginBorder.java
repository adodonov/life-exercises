package ru.spb.aboutweb2.life.UI;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 16.09.2012
 * Time: 21:15:21
 * To change this template use File | Settings | File Templates.
 */
public class OriginBorder {
    private Set<Segment> segments = new HashSet<Segment>();

    public void addSegment(Segment seg) {
        segments.add(seg);    
    }

    public void addSegment(Coords p1, Coords p2) {
        segments.add(new Segment(p1, p2));    
    }

    public Set<Segment> getSegments() {
        return segments;
    }

    @NotNull
    public boolean isEmpty() {
        return segments == null || segments.size() == 0;
    }


}
