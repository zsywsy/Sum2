package com.zsy.recyclerviewdemo;

public class Info {
        private String titleText;
        private String btnText;

        public Info() {
        }

        public Info(String titleText, String btnText) {
            this.titleText = titleText;
            this.btnText = btnText;
        }

        public String getTitleText() {
            return titleText;
        }

        public void setTitleText(String titleText) {
            this.titleText = titleText;
        }

        public String getBtnText() {
            return btnText;
        }

        public void setBtnText(String btnText) {
            this.btnText = btnText;
        }
    }