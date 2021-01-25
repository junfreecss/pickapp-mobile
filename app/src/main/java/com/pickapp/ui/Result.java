package com.pickapp.ui;

public class Result {

    public static class Success<T> extends Result {

        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public static class Error extends Result {

        private Throwable error;

        public Error(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return this.error;
        }
    }

}
