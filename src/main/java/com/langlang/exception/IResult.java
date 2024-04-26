package com.langlang.exception;

public interface IResult {

    public static final IResult SUCCESS = new IResult() {
        public String code() {
            return "0000";
        }

        public String message() {
            return "Success";
        }
    };

    public static final IResult SYSTEM_ERROR = new IResult() {
        public String code() {
            return "9999";
        }

        public String message() {
            return "System Unknown Exception";
        }
    };

    public static final IResult ILLEGAL_ARGUMENT = new IResult() {
        public String code() {
            return "4999";
        }

        public String message() {
            return "Illegal Arguments";
        }
    };

    String code();

    String message();
}
