package com.vungle.publisher;

import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdConfig implements a {
    final h a;

    @Inject
    public AdConfig() {
        this.a = new h();
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.a.isBackButtonImmediatelyEnabled();
    }

    public void setBackButtonImmediatelyEnabled(boolean isBackButtonImmediatelyEnabled) {
        this.a.a(isBackButtonImmediatelyEnabled);
    }

    public String getExtra1() {
        return a(1);
    }

    public void setExtra1(String value) {
        a(1, value);
    }

    public String getExtra2() {
        return a(2);
    }

    public void setExtra2(String value) {
        a(2, value);
    }

    public String getExtra3() {
        return a(3);
    }

    public void setExtra3(String value) {
        a(3, value);
    }

    public String getExtra4() {
        return a(4);
    }

    public void setExtra4(String value) {
        a(4, value);
    }

    public String getExtra5() {
        return a(5);
    }

    public void setExtra5(String value) {
        a(5, value);
    }

    public String getExtra6() {
        return a(6);
    }

    public void setExtra6(String value) {
        a(6, value);
    }

    public String getExtra7() {
        return a(7);
    }

    public void setExtra7(String value) {
        a(7, value);
    }

    public String getExtra8() {
        return a(8);
    }

    public void setExtra8(String value) {
        a(8, value);
    }

    private String a(int i) {
        return this.a.a("extra" + i);
    }

    private void a(int i, String str) {
        this.a.a("extra" + i, str);
    }

    public Map<String, String> getExtras() {
        return this.a.getExtras();
    }

    public boolean isImmersiveMode() {
        return this.a.isImmersiveMode();
    }

    public void setImmersiveMode(boolean isImmersiveMode) {
        this.a.b(isImmersiveMode);
    }

    public boolean isIncentivized() {
        return this.a.isIncentivized();
    }

    public void setIncentivized(boolean isIncentivized) {
        this.a.c(isIncentivized);
    }

    public String getIncentivizedCancelDialogBodyText() {
        return this.a.getIncentivizedCancelDialogBodyText();
    }

    public void setIncentivizedCancelDialogBodyText(String bodyText) {
        this.a.b(bodyText);
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        return this.a.getIncentivizedCancelDialogCloseButtonText();
    }

    public void setIncentivizedCancelDialogCloseButtonText(String closeButtonText) {
        this.a.c(closeButtonText);
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        return this.a.getIncentivizedCancelDialogKeepWatchingButtonText();
    }

    public void setIncentivizedCancelDialogKeepWatchingButtonText(String keepWatchingButtonText) {
        this.a.d(keepWatchingButtonText);
    }

    public String getIncentivizedCancelDialogTitle() {
        return this.a.getIncentivizedCancelDialogTitle();
    }

    public void setIncentivizedCancelDialogTitle(String title) {
        this.a.e(title);
    }

    public String getIncentivizedUserId() {
        return this.a.getIncentivizedUserId();
    }

    public void setIncentivizedUserId(String incentivizedUserId) {
        this.a.f(incentivizedUserId);
    }

    public Orientation getOrientation() {
        return this.a.getOrientation();
    }

    public void setOrientation(Orientation orientation) {
        this.a.a(orientation);
    }

    public String getPlacement() {
        return this.a.getPlacement();
    }

    public void setPlacement(String placement) {
        this.a.g(placement);
    }

    public boolean isSoundEnabled() {
        return this.a.isSoundEnabled();
    }

    public void setSoundEnabled(boolean isSoundEnabled) {
        this.a.d(isSoundEnabled);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        return this.a.equals(obj);
    }

    public String toString() {
        return this.a.toString();
    }
}
