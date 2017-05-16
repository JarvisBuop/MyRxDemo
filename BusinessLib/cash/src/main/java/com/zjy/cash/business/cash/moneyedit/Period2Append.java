package com.zjy.cash.business.cash.moneyedit;

import java.text.DecimalFormat;

public  class Period2Append extends AbsMoneyEditAppend {
        public Period2Append(String source) {
            super(source);
        }

    @Override
    public String replaceText(double money) {
        money=money*100;
        return super.replaceText(money);
    }

    public String appendNum(String keyName) {

            String cleanString = (source + keyName).replaceAll("[¥,.]", "");
            double parsed = Double.parseDouble(cleanString);
            if (parsed > 9999999999.99) {
                return source;
            }

            String formatted = new DecimalFormat("0.00").format((parsed / 100));
            return "¥" + formatted;
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
            String formatted = new DecimalFormat("0.00").format((parsed / 100));
            return "¥" + formatted;
        }

        @Override
        public String getDefaultText() {
            return "¥0.00";
        }
    }