package com.ijys.testapiclient;

import com.ijys.internals.lib.SimpleApiGrpc;
import com.ijys.internals.lib.SimpleReply;
import com.ijys.internals.lib.SimpleRequest;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcApiClient implements CommandLineRunner {

    @GrpcClient("simpleapi")
    private SimpleApiGrpc.SimpleApiBlockingStub simpleApiBlockingStub;

    public static void main(String[] args) {
        SpringApplication.run(GrpcApiClient.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start Console boot application");
        try {
            SimpleRequest request = SimpleRequest.newBuilder().setName("jaye.jung").build();
            SimpleReply response = simpleApiBlockingStub.getSimpleData(request);
            System.out.println(response.toString());

        } catch (StatusRuntimeException e) {
            System.out.println("Failed with " + e.getStatus().getCode().name());
        }
    }
}
