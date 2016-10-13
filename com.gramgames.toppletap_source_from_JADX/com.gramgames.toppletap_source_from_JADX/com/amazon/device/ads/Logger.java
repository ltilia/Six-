package com.amazon.device.ads;

interface Logger {
    void d(String str);

    void e(String str);

    void i(String str);

    void v(String str);

    void w(String str);

    Logger withLogTag(String str);
}
