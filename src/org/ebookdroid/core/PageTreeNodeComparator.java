package org.ebookdroid.core;

import org.ebookdroid.utils.CompareUtils;

import android.graphics.RectF;

import java.util.Comparator;

public class PageTreeNodeComparator implements Comparator<PageTreeNode> {

    final ViewState viewState;

    public PageTreeNodeComparator(final ViewState viewState) {
        this.viewState = viewState;
    }

    @Override
    public int compare(final PageTreeNode node1, final PageTreeNode node2) {
        final int cp = viewState.currentIndex;
        final int viewIndex1 = node1.page.index.viewIndex;
        final int viewIndex2 = node2.page.index.viewIndex;

        final boolean v1 = viewState.isNodeVisible(node1, viewState.getBounds(node1.page));
        final boolean v2 = viewState.isNodeVisible(node2, viewState.getBounds(node2.page));

        int res = 0;

        if (v1 && !v2) {
            res = -1;
        } else if (!v1 && v2) {
            res = 1;
        } else {

            final RectF s1 = node1.pageSliceBounds;
            final RectF s2 = node2.pageSliceBounds;

            if (viewIndex1 == cp && viewIndex2 == cp) {
                res = CompareUtils.compare(s1.top, s2.top);
                if (res == 0) {
                    res = CompareUtils.compare(s1.left, s2.left);
                }
            } else {
                final float d1 = viewIndex1 + s1.top - (cp + 0.5f);
                final float d2 = viewIndex2 + s2.top - (cp + 0.5f);
                final int dist1 = Math.abs((int) (d1 * node1.childrenZoomThreshold));
                final int dist2 = Math.abs((int) (d2 * node2.childrenZoomThreshold));
                res = CompareUtils.compare(dist1, dist2);
                if (res == 0) {
                    res = -CompareUtils.compare(viewIndex1, viewIndex2);
                }
            }
        }
        return res;
    }
}