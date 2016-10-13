package com.amazon.device.ads;

import gs.gram.mopub.BuildConfig;

class Parsers {

    public static class IntegerParser {
        private int defaultValue;
        private final MobileAdsLogger logger;
        private String parseErrorLogMessage;
        private String parseErrorLogTag;

        public IntegerParser() {
            this(new MobileAdsLoggerFactory());
        }

        IntegerParser(MobileAdsLoggerFactory loggerFactory) {
            this.logger = loggerFactory.createMobileAdsLogger(BuildConfig.FLAVOR);
        }

        public IntegerParser setDefaultValue(int defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public IntegerParser setParseErrorLogMessage(String parseErrorLogMessage) {
            this.parseErrorLogMessage = parseErrorLogMessage;
            return this;
        }

        public IntegerParser setParseErrorLogTag(String parseErrorLogTag) {
            this.parseErrorLogTag = parseErrorLogTag;
            this.logger.withLogTag(this.parseErrorLogTag);
            return this;
        }

        public int parse(String text) {
            int parsedValue = this.defaultValue;
            if (!StringUtils.isNullOrWhiteSpace(text)) {
                try {
                    parsedValue = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    if (!(this.parseErrorLogTag == null || this.parseErrorLogMessage == null)) {
                        this.logger.w(this.parseErrorLogMessage);
                    }
                }
            }
            return parsedValue;
        }
    }

    Parsers() {
    }
}
