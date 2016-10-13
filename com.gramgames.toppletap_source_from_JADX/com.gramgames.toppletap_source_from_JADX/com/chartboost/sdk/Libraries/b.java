package com.chartboost.sdk.Libraries;

import com.adjust.sdk.Constants;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public final class b {
    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance(Constants.SHA1);
                instance.update(bArr);
                bArr2 = instance.digest();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return bArr2;
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        return String.format(Locale.US, "%0" + (bArr.length << 1) + "x", new Object[]{bigInteger});
    }
}
