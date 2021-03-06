package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.json.simple.parser.Yytoken;

class VastResource implements Serializable {
    private static final List<String> VALID_APPLICATION_TYPES;
    private static final List<String> VALID_IMAGE_TYPES;
    private static final long serialVersionUID = 0;
    @NonNull
    private CreativeType mCreativeType;
    private int mHeight;
    @NonNull
    private String mResource;
    @NonNull
    private Type mType;
    private int mWidth;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$VastResource$Type;

        static {
            $SwitchMap$com$mopub$mobileads$VastResource$Type = new int[Type.values().length];
            try {
                $SwitchMap$com$mopub$mobileads$VastResource$Type[Type.STATIC_RESOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$mobileads$VastResource$Type[Type.HTML_RESOURCE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$mobileads$VastResource$Type[Type.IFRAME_RESOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    enum CreativeType {
        NONE,
        IMAGE,
        JAVASCRIPT
    }

    enum Type {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE
    }

    static {
        VALID_IMAGE_TYPES = Arrays.asList(new String[]{"image/jpeg", "image/png", "image/bmp", "image/gif"});
        VALID_APPLICATION_TYPES = Arrays.asList(new String[]{"application/x-javascript"});
    }

    @Nullable
    static VastResource fromVastResourceXmlManager(@NonNull VastResourceXmlManager resourceXmlManager, int width, int height) {
        for (Type type : Type.values()) {
            VastResource vastResource = fromVastResourceXmlManager(resourceXmlManager, type, width, height);
            if (vastResource != null) {
                return vastResource;
            }
        }
        return null;
    }

    @Nullable
    static VastResource fromVastResourceXmlManager(@NonNull VastResourceXmlManager resourceXmlManager, @NonNull Type type, int width, int height) {
        String resource;
        CreativeType creativeType;
        Preconditions.checkNotNull(resourceXmlManager);
        Preconditions.checkNotNull(type);
        String iFrameResource = resourceXmlManager.getIFrameResource();
        String htmlResource = resourceXmlManager.getHTMLResource();
        String staticResource = resourceXmlManager.getStaticResource();
        String staticResourceType = resourceXmlManager.getStaticResourceType();
        if (type == Type.STATIC_RESOURCE && staticResource != null && staticResourceType != null && (VALID_IMAGE_TYPES.contains(staticResourceType) || VALID_APPLICATION_TYPES.contains(staticResourceType))) {
            resource = staticResource;
            if (VALID_IMAGE_TYPES.contains(staticResourceType)) {
                creativeType = CreativeType.IMAGE;
            } else {
                creativeType = CreativeType.JAVASCRIPT;
            }
        } else if (type == Type.HTML_RESOURCE && htmlResource != null) {
            resource = htmlResource;
            creativeType = CreativeType.NONE;
        } else if (type != Type.IFRAME_RESOURCE || iFrameResource == null) {
            return null;
        } else {
            resource = iFrameResource;
            creativeType = CreativeType.NONE;
        }
        return new VastResource(resource, type, creativeType, width, height);
    }

    VastResource(@NonNull String resource, @NonNull Type type, @NonNull CreativeType creativeType, int width, int height) {
        Preconditions.checkNotNull(resource);
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(creativeType);
        this.mResource = resource;
        this.mType = type;
        this.mCreativeType = creativeType;
        this.mWidth = width;
        this.mHeight = height;
    }

    @NonNull
    public String getResource() {
        return this.mResource;
    }

    @NonNull
    public Type getType() {
        return this.mType;
    }

    @NonNull
    public CreativeType getCreativeType() {
        return this.mCreativeType;
    }

    public void initializeWebView(@NonNull VastWebView webView) {
        Preconditions.checkNotNull(webView);
        if (this.mType == Type.IFRAME_RESOURCE) {
            webView.loadData("<iframe frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" style=\"border: 0px; margin: 0px;\" width=\"" + this.mWidth + "\" height=\"" + this.mHeight + "\" src=\"" + this.mResource + "\"></iframe>");
        } else if (this.mType == Type.HTML_RESOURCE) {
            webView.loadData(this.mResource);
        } else if (this.mType != Type.STATIC_RESOURCE) {
        } else {
            if (this.mCreativeType == CreativeType.IMAGE) {
                webView.loadData("<html><head></head><body style=\"margin:0;padding:0\"><img src=\"" + this.mResource + "\" width=\"100%\" style=\"max-width:100%;max-height:100%;\" />" + "</body>" + "</html>");
            } else if (this.mCreativeType == CreativeType.JAVASCRIPT) {
                webView.loadData("<script src=\"" + this.mResource + "\"></script>");
            }
        }
    }

    @Nullable
    public String getCorrectClickThroughUrl(@Nullable String vastClickThroughUrl, @Nullable String webViewClickThroughUrl) {
        switch (1.$SwitchMap$com$mopub$mobileads$VastResource$Type[this.mType.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                if (CreativeType.IMAGE == this.mCreativeType) {
                    return vastClickThroughUrl;
                }
                if (CreativeType.JAVASCRIPT != this.mCreativeType) {
                    return null;
                }
                return webViewClickThroughUrl;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return webViewClickThroughUrl;
            default:
                return null;
        }
    }
}
