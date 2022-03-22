package com.ijys.testapiserver.service;

import com.ijys.internals.lib.SimpleApiGrpc;
import com.ijys.internals.lib.SimpleReply;
import com.ijys.internals.lib.SimpleRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SimpleApiService extends SimpleApiGrpc.SimpleApiImplBase {
    @Override
    public void getSimpleData(SimpleRequest request, StreamObserver<SimpleReply> responseObserver) {
        SimpleReply reply = SimpleReply.newBuilder()
                .setId(1L)
                .setData(String.format("My name is %s", request.getName()))
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
