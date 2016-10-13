package com.amazon.device.ads;

interface OnAdReceivedCommand {
    ActionCode onAdReceived(Ad ad, AdData adData);
}
