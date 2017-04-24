package com.zjy.cash.business.cash.moneyedit;

import java.text.NumberFormat;

public  class Period2Append extends AbsMoneyEditAppend {
        public Period2Append(String source) {
            super(source);
        }

        public String appendNum(String keyName) {

            String cleanString = (source + keyName).replaceAll("[¥,.]", "");
            double parsed = Double.parseDouble(cleanString);
            if (parsed > 9999999999.99) {
                return source;
            }
            String formatted = NumberFormat.getNumberInstance().format((parsed / 100));
            return "¥" + formatted.replaceAll(",","");
        }

        @Override
        public String appendPeriod(String keyName) {
            return source;
        }

        @Override
        public String appendDel() {
            String cleanString = source.substring(0, source.length() - 1).replaceAll("[¥,.]", "");
            double parsed = Double.parseDouble(cleanString);
            if (parsed == 0) {
                return getDefaultText();
            }
            String formatted = NumberFormat.getNumberInstance().format((parsed / 100));
            return "¥" + formatted.replaceAll(",","");
        }

        @Override
        public String getDefaultText() {
            return "¥0.00";
        }
    }