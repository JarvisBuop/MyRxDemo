package com.zjy.cash.business.cash.moneyedit;

public  class Period0Append extends AbsMoneyEditAppend {
        @Override
        public String getDefaultText() {
            return "¥0";
        }

        @Override
        public String appendNum(String keyName) {
            String cleanString = source.replaceAll("¥", "");
            double parsed = Double.parseDouble(cleanString);
            double resulParsed =Double.parseDouble((source+keyName).replace("¥",""));
            if(parsed==0&&!source.contains(".")){
                return source.replace("0",keyName);
            }
            if(resulParsed>99999999.99){
                return source;
            }
            if(source.contains(".")&&source.indexOf(".")<source.length()-2){
                return source;
            }
            return  source+keyName;
        }

        @Override
        public String appendPeriod(String keyName) {
            if(source.contains(keyName)){
                return source;
            }
            return source+keyName;
        }

        @Override
        public String appendDel() {
           if(source.length()<=2){
               return getDefaultText();
           }
            return source.substring(0,source.length()-1);
        }

        public Period0Append(String source) {
            super(source);
        }
    }