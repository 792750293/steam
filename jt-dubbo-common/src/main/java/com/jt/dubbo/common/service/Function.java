package com.jt.dubbo.common.service;

public interface Function<E, T> {

    public T execute(E e);

}
