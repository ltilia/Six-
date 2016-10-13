package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

public class VastExtensionXmlManager {
    public static final String TYPE = "type";
    public static final String VIDEO_VIEWABILITY_TRACKER = "MoPubViewabilityTracker";
    private final Node mExtensionNode;

    public VastExtensionXmlManager(@NonNull Node extensionNode) {
        Preconditions.checkNotNull(extensionNode);
        this.mExtensionNode = extensionNode;
    }

    @Nullable
    VideoViewabilityTracker getVideoViewabilityTracker() {
        Node videoViewabilityTrackerNode = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, VIDEO_VIEWABILITY_TRACKER);
        if (videoViewabilityTrackerNode == null) {
            return null;
        }
        VideoViewabilityTrackerXmlManager videoViewabilityTrackerXmlManager = new VideoViewabilityTrackerXmlManager(videoViewabilityTrackerNode);
        Integer viewablePlaytime = videoViewabilityTrackerXmlManager.getViewablePlaytimeMS();
        Integer percentViewable = videoViewabilityTrackerXmlManager.getPercentViewable();
        String videoViewabilityTrackerUrl = videoViewabilityTrackerXmlManager.getVideoViewabilityTrackerUrl();
        if (viewablePlaytime == null || percentViewable == null || TextUtils.isEmpty(videoViewabilityTrackerUrl)) {
            return null;
        }
        return new VideoViewabilityTracker(viewablePlaytime.intValue(), percentViewable.intValue(), videoViewabilityTrackerUrl);
    }

    @Nullable
    String getType() {
        return XmlUtils.getAttributeValue(this.mExtensionNode, TYPE);
    }
}
